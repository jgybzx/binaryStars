package com.jgybzx.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @author jgybzx
 * @date 2020/11/26 19:54
 * @description
 */
public class Jgyb {
    public static void main(String[] args) throws IOException {
        Map<String, String> keyPair = RsaEncryptUtils.createKeyPair();
        String pubKey = keyPair.get("pub");
        String priKey = keyPair.get("pri");
        System.out.printf("pubKey:" + pubKey);
        System.out.println("###########################");
        System.out.printf("priKey:" + priKey);
        //文件放在src/main/java/ 目录下 命名为aaa.java
        File file = new File("src/main/resource/aaa.txt");
        //如果文件不存在，创建一个文件
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        PrintStream ps = new PrintStream(new FileOutputStream(file));
        ps.println(pubKey);
        ps.append(priKey);


        String text = "jgybzx";
        // 获取公钥
        RSAPublicKey rsaPubKey = RsaEncryptUtils.getRsaPubKey(pubKey);
        // 公钥加密
        String s = RsaEncryptUtils.encodeByPub(text, rsaPubKey);
        System.out.println("###########################");
        System.out.println(s);
        // 获取私钥
        RSAPrivateKey rsaPriKey = RsaEncryptUtils.getRsaPriKey(priKey);
        // 私钥解密
        String s1 = RsaEncryptUtils.decodeByPri(s, rsaPriKey);
        System.out.println("###########################");
        System.out.println(s1);
    }
}
