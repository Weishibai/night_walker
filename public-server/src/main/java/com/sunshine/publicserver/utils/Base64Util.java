package com.sunshine.publicserver.utils;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {

    public static String encodeToStr(byte[] src) {
        return new String(Base64.encodeBase64(src));
    }

    public static byte[] decodeToByte(String src) {
        return Base64.decodeBase64(src.getBytes());
    }

    public static String wrapEncode(String content, String token) {
        return encodeToStr(AesUtil.encrypt(content, token));
    }

    public static String wrapDecode(String content, String token) {
        return AesUtil.decrypt(Base64.decodeBase64(content.getBytes()), token);
    }


}
