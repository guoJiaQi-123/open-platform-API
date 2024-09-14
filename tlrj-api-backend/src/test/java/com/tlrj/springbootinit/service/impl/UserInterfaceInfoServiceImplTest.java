package com.tlrj.springbootinit.service.impl;


import com.tlrj.springbootinit.service.UserInterfaceInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;

import javax.annotation.Resource;

@SpringBootTest
public class UserInterfaceInfoServiceImplTest {


    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Test
    public void invokeCount() {
        userInterfaceInfoService.invokeCount(1L, 1L);
    }
}