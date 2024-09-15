package com.tyut.tlrjapigateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class TlrjApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(TlrjApiGatewayApplication.class, args);
        log.info("******************* 项目启动成功 *******************");
    }

}
