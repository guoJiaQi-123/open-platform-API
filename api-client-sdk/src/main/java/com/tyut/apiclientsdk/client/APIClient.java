package com.tyut.apiclientsdk.client;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.tyut.apiclientsdk.model.User;

import java.util.HashMap;
import java.util.Map;

import static com.tyut.apiclientsdk.utils.SignUtil.getSign;


/**
 * @version v1.0
 * @author OldGj 2024/9/11
 * @apiNote 客户端
 */
@SuppressWarnings("all")
public class APIClient {

    //访问密钥
    private String accessKey;
    // 秘密密钥
    private String secretKey;

    public APIClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    /**
     * 调用服务端的get方法获取响应内容
     * @param name
     * @return
     */
    public String getNameByGET(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.get("http://localhost:6677/api/name/", paramMap);
        return result;
    }


    /**
     * 调用服务端的POST方法获取响应内容
     * @param name
     * @return
     */
    public String getNameByPost(String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.post("http://localhost:6677/api/name/", paramMap);
        return result;
    }


    /**
     * 构造请求头参数
     * @param body
     * @return
     */
    private Map<String, String> getHeaderMap(String body) {
        Map<String, String> map = new HashMap<>();
        map.put("accessKey", accessKey);
        map.put("nonce", RandomUtil.randomNumbers(4));
        map.put("timestamp", String.valueOf(System.currentTimeMillis()));
        map.put("body", body);
        map.put("sign", getSign(body, secretKey));
        return map;
    }


    /**
     * 调用服务端的POST方法，参数以对象的形式传输
     * @param user
     * @return
     */
    public String getNameByPost(User user) {
        // 改为json格式
        String jsonStr = JSONUtil.toJsonStr(user);
        // 获取请求头参数
        Map<String, String> headerMap = getHeaderMap(jsonStr);
        // 封装请求
        HttpResponse httpResponse = HttpRequest.post("http://localhost:6677/api/name/user")
                .addHeaders(headerMap)
                .body(jsonStr)
                .execute();
        return httpResponse.body();
    }

}
