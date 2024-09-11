package com.tyut.tlrjinterface;

import com.tyut.apiclientsdk.client.APIClient;
import com.tyut.apiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class TlrjInterfaceApplicationTests {

    @Resource
    private APIClient apiClient;

    @Test
    void contextLoads() {
        String name = apiClient.getNameByPost(new User("shumazai"));
        System.out.println(name);
    }

}
