package com.tlrj.springbootinit.service;

import java.util.concurrent.CompletableFuture;

/**
 * @version v1.0
 * @apiNote 测试RPC调用demo
 * @author OldGj 2024/9/18
 */

public interface DemoService {


    String sayHello(String name);

    String sayHello2(String name);

    default CompletableFuture<String> sayHelloAsync(String name) {
        return CompletableFuture.completedFuture(sayHello(name));
    }

}
