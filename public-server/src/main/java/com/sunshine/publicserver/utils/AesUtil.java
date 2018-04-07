package com.sunshine.publicserver.utils;

import com.google.common.base.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AesUtil {

    private static final Logger logger = LoggerFactory.getLogger(AesUtil.class);

    private AesUtil() {
    }

    /**
     * 加密
     * 
     * @param content 需要加密的内容
     * @param password 加密密码
     */
    public static byte[] encrypt(String content, String password) {
        try {
            byte[] raw = password.getBytes(Charsets.UTF_8);
            if (raw.length != 16) {
                throw new IllegalArgumentException("Invalid key size." + password);
            }

            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[16])); // zero IV
            return cipher.doFinal(content.getBytes(Charsets.UTF_8));
            // KeyGenerator kgen = KeyGenerator.getInstance("AES");
            // SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            // secureRandom.setSeed(password.getBytes());
            // kgen.init(128, secureRandom);
            // SecretKey secretKey = kgen.generateKey();
            // byte[] enCodeFormat = secretKey.getEncoded();
            // SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            // byte[] byteContent = content.getBytes(ENCODE);
            // cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            // byte[] result = cipher.doFinal(byteContent);
            // return result; // 加密
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static String decrypt(byte[] content, String password) {
        try {
            byte[] raw = password.getBytes(Charsets.UTF_8);
            if (raw.length != 16) {
                throw new IllegalArgumentException("Invalid key size. " + password);
            }
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[16]));
            byte[] original = cipher.doFinal(content);

            return new String(original, Charsets.UTF_8);
            // KeyGenerator kgen = KeyGenerator.getInstance("AES");
            // SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            // secureRandom.setSeed(password.getBytes());
            // kgen.init(128, secureRandom);
            // SecretKey secretKey = kgen.generateKey();
            // byte[] enCodeFormat = secretKey.getEncoded();
            // SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            // cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            // byte[] result = cipher.doFinal(content);
            // return new String(result, ENCODE);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }


    public static void main(String[] args) throws IOException {

//        String xx = "S1N1c1NIZ0VNZlZ4S1B5ekg3cGFveGQ5eFR6d2piMEh2K1picHJqbHVWQW1MTWlmbk8vc0VJZWNuK2tFbThaeg==";
//        String result = decrypt(Base64.decodeBase64(Base64.decodeBase64(xx.getBytes(Charsets.UTF_8))), "1234567890123456");
//        System.out.println(result);
        
        String content="{\"status\": 1,\"msg\": \"Invalid parameter(4)\"}";
        InputStream in = System.in;
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        while(br.readLine() != null){
            System.out.println();
        }
        System.out.println(new String(Base64.encodeBase64(encrypt(content, "1234567890123456"))));
    }
}
