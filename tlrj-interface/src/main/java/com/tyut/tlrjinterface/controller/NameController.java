package com.tyut.tlrjinterface.controller;


import com.tyut.apiclientsdk.model.User;
import com.tyut.apiclientsdk.utils.SignUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @version v1.0
 * @author OldGj 2024/9/11
 * @apiNote 名称控制器
 */
@RequestMapping("/name")
@RestController
public class NameController {

    @GetMapping("/")
    public String getNameGET(String name) {
        return "GET 你的名字是：" + name;
    }

    @PostMapping("/")
    public String getNamePOST(@RequestParam String name) {
        return "POST 你的名字是：" + name;
    }

    @PostMapping("/user")
    public String getUserNamePOST(@RequestBody User user, HttpServletRequest request) {
        String accessKey = request.getHeader("accessKey");
        // TODO 这里应该去数据库查询用户的accessKey
        if (!accessKey.equals("ababab")) {
            throw new RuntimeException("无权限");
        }
        // 校验随机数，防止重放攻击
        String nonce = request.getHeader("nonce");
        if (Integer.valueOf(nonce) > 10000) {
            throw new RuntimeException("随机数错误");
        }
        // 校验时间戳
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        String body = request.getHeader("body");
        // TODO 这里应该去数据库中查询用户的 secretKey
        String createSign = SignUtil.getSign(body, "abcdefgh");
        if (!sign.equals(createSign)) {
            throw new RuntimeException("签名错误");
        }
        return "POST 你的名字是：" + user.getName();
    }
}
