package com.tyut.tlrjapigateway;

import com.tyut.apiclientsdk.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


/**
 * 全局过滤器（所有走网关的请求都会调用这个方法）
 */
@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    public final List<String> WHITE_LIST = new ArrayList<>();


    @Override
    @SuppressWarnings("all")
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 1.请求日志
        String requestId = request.getId();
        String path = request.getPath().value();
        String methodName = request.getMethod().name();
        String params = request.getQueryParams().toString();
        String localAddress = request.getLocalAddress().getAddress().getHostAddress();
        log.info("请求唯一标识：{}", requestId);
        log.info("请求路径：{}", path);
        log.info("请求方法：{}", methodName);
        log.info("请求参数：{}", params);
        log.info("源路径：{}", localAddress);
        // 2.黑白名单
        if (!WHITE_LIST.contains(localAddress)) {
            return handleNoAuth(response); // 返回403异常码，拒绝访问
        }
        // 3.统一鉴权，判断用户的accessKey和secretKey是否合法，这个accessKey和secretKey要从header头里获取
        HttpHeaders requestHeaders = request.getHeaders();
        String accessKey = requestHeaders.getFirst("accessKey"); // accessKey
        String nonce = requestHeaders.getFirst("nonce"); // 随机数
        String timestamp = requestHeaders.getFirst("timestamp"); // 时间戳
        String sign = requestHeaders.getFirst("sign"); // 签名
        String body = requestHeaders.getFirst("body"); // 请求体
        // TODO 这里应该去数据库查询用户的accessKey
        if (!"ababab".equals(accessKey)) { // 校验accessKey
            return handleNoAuth(response); // 返回403异常码，拒绝访问
        }
        if (nonce != null && Integer.parseInt(nonce) > 10000) { // 校验随机数
            return handleNoAuth(response);
        }
        long currentTime = System.currentTimeMillis() / 1000;
        long FIVE_MINUTES = 5 * 60L;
        if (timestamp != null && currentTime - Long.parseLong(timestamp) > FIVE_MINUTES) { // 校验时间戳
            return handleNoAuth(response);
        }
        // TODO 这里的secretKey应该从数据库中查询
        String createSign = SignUtil.getSign(body, "abcdefgh");
        if (sign != null && !sign.equals(createSign)) {
            return handleNoAuth(response);
        }
        // TODO 4.判断请求的模拟接口是否存在，需要查询数据库
        // 5.请求转发，调用模拟接口
        return decoratedResponse(exchange, chain);
    }

    /**
     * 利用 response 装饰者，增强原有 response 的处理能力
     * @param exchange
     * @param chain
     * @return
     */
    public Mono<Void> decoratedResponse(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            ServerHttpResponse originalResponse = exchange.getResponse();
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();
            HttpStatus statusCode = (HttpStatus) originalResponse.getStatusCode();
            if (statusCode != HttpStatus.OK) {
                return chain.filter(exchange);//降级处理返回数据
            }
            ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
                /**
                 * 在模拟接口调用结束后会触发这个方法，在这个方法中去处理调用次数的修改和打日志，类似于回调
                 */
                @Override
                public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                    if (body instanceof Flux) {
                        Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                        return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
                            // 合并多个流集合，解决返回体分段传输
                            DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                            DataBuffer buff = dataBufferFactory.join(dataBuffers);
                            byte[] content = new byte[buff.readableByteCount()];
                            buff.read(content);
                            DataBufferUtils.release(buff);//释放掉内存
                            // 6.响应日志
                            String joinData = new String(content);
                            log.info("响应内容：{}", joinData);
                            // TODO 7.调用成功，接口调用次数+1（网关层面）
                            return bufferFactory.wrap(joinData.getBytes());
                        }));
                    } else {
                        log.error("<-- {} 响应code异常", getStatusCode());
                        // 8.调用失败，返回一个规范的错误码
                    }
                    return super.writeWith(body);
                }
            };
            return chain.filter(exchange.mutate().response(decoratedResponse).build());

        } catch (Exception e) {
            log.error("网关转发请求出错：", e);
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return -1;
    }


    /**
     * 校验逻辑错误后，调用该方法返回403异常码拒绝校验失败的用户访问
     * @param response
     * @return
     */
    private Mono<Void> handleNoAuth(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FORBIDDEN); // 返回403异常码，拒绝访问
        return response.setComplete();
    }
}