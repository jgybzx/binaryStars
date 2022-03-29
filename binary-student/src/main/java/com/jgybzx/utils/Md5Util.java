package com.jgybzx.utils;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author jgybzx
 * @date 2021/1/13 14:45
 * @description MD5加密工具
 */
public class Md5Util {
    private Md5Util() {
    }

    protected static final String[] HEX_DIGITS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String md5(String plainText) {
        return md5(plainText.getBytes());
    }

    public static String md5(InputStream file) {
        byte[] byteFile = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream(1024)) {
            byte[] temp = new byte[1024];
            int size = 0;
            while ((size = file.read(temp)) != -1) {
                out.write(temp, 0, size);
            }
            byteFile = out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return md5(byteFile);
    }

    public static String md5(byte[] byteFile) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(byteFile);
            return byteArrayToHexString(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取文件的MD5值
     *
     * @param file
     * @return
     */
    public static String md5(File file) {
        try (FileInputStream fs = new FileInputStream(file)) {
            return Md5Util.md5(fs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 字节数组转十六进制字符串
     *
     * @param digest
     * @return
     */
    private static String byteArrayToHexString(byte[] digest) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            buffer.append(byteToHexString(digest[i]));
        }
        return buffer.toString();
    }

    /**
     * 字节转十六进制字符串
     *
     * @param b
     * @return
     */
    private static String byteToHexString(byte b) {

        int d1 = (b & 0xf0) >> 4;

        int d2 = b & 0xf;
        return HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }
}
