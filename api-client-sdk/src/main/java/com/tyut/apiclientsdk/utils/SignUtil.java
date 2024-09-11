package com.tyut.apiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * @version v1.0
 * @author OldGj 2024/9/11
 * @apiNote 签名工具类
 */
public class SignUtil {

    public static String getSign(String body, String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String context = body + "." + secretKey;
        return md5.digestHex(context);
    }
}
