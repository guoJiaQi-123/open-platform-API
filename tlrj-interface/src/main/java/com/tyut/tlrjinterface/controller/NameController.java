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

    @GetMapping("/get")
    public String getNameGET(String name) {
        return "GET 你的名字是：" + name;
    }

    @PostMapping("/post")
    public String getNamePOST(@RequestParam String name) {
        return "POST 你的名字是：" + name;
    }

    @PostMapping("/user")
    public String getUserNamePOST(@RequestBody User user, HttpServletRequest request) {
        return "POST 你的名字是：" + user.getName();
    }
}
