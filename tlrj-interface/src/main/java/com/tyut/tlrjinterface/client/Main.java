package com.tyut.tlrjinterface.client;

import com.tyut.tlrjinterface.model.User;

/**
 * @version v1.0
 * @author OldGj 2024/9/11
 * @apiNote 主函数
 */
public class Main {
    public static void main(String[] args) {
        String accessKey = "ababab";

        String secretKey = "abcdefgh";
        APIClient apiClient = new APIClient(accessKey, secretKey);
        apiClient.getNameByPost(new User("shumazai"));


    }
}
