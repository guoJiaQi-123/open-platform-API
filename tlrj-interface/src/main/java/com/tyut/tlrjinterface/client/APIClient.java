package com.tyut.tlrjinterface.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.tyut.tlrjinterface.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

import static com.tyut.tlrjinterface.utils.SignUtil.getSign;

/**
 * @version v1.0
 * @author OldGj 2024/9/11
 * @apiNote 客户端
 */
@SuppressWarnings("all")
public class APIClient {

    private String accessKey;

    private String secretKey;

    public APIClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }


    public String getNameByGET(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.get("http://localhost:6677/api/name/", paramMap);
        System.out.println(result);
        return result;
    }


    public String getNameByPost(@RequestParam String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.post("http://localhost:6677/api/name/", paramMap);
        System.out.println(result);
        return result;
    }

    private Map<String, String> getHeaderMap(String body) {
        Map<String, String> map = new HashMap<>();
        map.put("accessKey", accessKey);
        map.put("nonce", RandomUtil.randomNumbers(4));
        map.put("timestamp", String.valueOf(System.currentTimeMillis()));
        map.put("body", body);
        map.put("sign", getSign(body, secretKey));
        return map;
    }


    public String getNameByPost(@RequestBody User user) {
        String jsonStr = JSONUtil.toJsonStr(user);
        Map<String, String> headerMap = getHeaderMap(jsonStr);
        HttpResponse httpResponse = HttpRequest.post("http://localhost:6677/api/name/user")
                .addHeaders(headerMap)
                .body(jsonStr)
                .execute();
        int status = httpResponse.getStatus();
        System.out.println("请求状态" + status);
        String result = httpResponse.body();
        System.out.println(result);
        return result;
    }

}
