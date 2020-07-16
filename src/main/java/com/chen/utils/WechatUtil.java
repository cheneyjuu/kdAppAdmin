package com.chen.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;

/**
 * @author chen
 **/
public class WechatUtil {
    private static final Logger log = LoggerFactory.getLogger(WechatUtil.class);

    private static final String KEY_ALGORITHM = "AES";
    private static final String ALGORITHM_STR = "AES/CBC/PKCS7Padding";
    private static Key key;
    private static Cipher cipher;

    public static void main(String[] args) {
        String result = decryptData(
            "0VAQS4f7I/aR/4t8AbfmoZKCAVGYIlnUQwVj6x5usR5lBFVNAOmY/P3nLs89wicwI5Xzb7Gsz/665B1U9eOQZtbyrU1C75nhSNsVVuI7FiypVhVvgpFgu8OERkKVwU+yCETsuQH1xkMMOs3gJRSoRG6n20ISGs85FFLh6TQbGD9rSpSlFlwXBETzyBBv7k41MRk08WfYaQXaaoaZxzMJQCUDpN9OHv/fNpdW5aZHfHJEJZRJ1FI3LsANI/AJgDK9ovTHj8uPPz76QTj5gDJiOJpmtwPh66dT44usznAQOZQW2lkN8FCvSURJXSSxA4zgchgVPcMcBdWUHzDbdBzqisvm3IX+L+Fx/+5ZvdJk/7m/PLBLkLmy44k8WyWJzQG4+1wmTP2QT1qK2n6UkZrJnCUSLWXUBt2luscJgWn/W+CvPKAj82mS51yB4KiASqu2sl00cKjR09w9zTWx9Q+SXg==",
            "+5jJCYIGkTN/1lNObgbTow==",
            "V/b/gqWzq1zLCMFUz8wMuQ=="
        );
        System.out.println("result = " + result);
    }

    /**
     * 微信关键信息解密
     *
     * @param encryptDataB64 encryptDataB64
     * @param sessionKeyB64 sessionKeyB64
     * @param ivB64 ivB64
     * @return code
     */
    public static String decryptData(String encryptDataB64, String sessionKeyB64, String ivB64) {
        log.info("decryptData method; encryptDataB64 => {}, sessionKeyB64 => {}, ivB64=> {}", encryptDataB64, sessionKeyB64, ivB64);
        return new String(
            decryptOfDiyIV(
                Base64.decode(encryptDataB64),
                Base64.decode(sessionKeyB64),
                Base64.decode(ivB64)
            )
        );
    }

    private static void init(byte[] keyBytes) {
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        int base = 16;
        if (keyBytes.length % base != 0) {
            int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
            keyBytes = temp;
        }
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        // 转化成JAVA的密钥格式
        key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        try {
            // 初始化cipher
            cipher = Cipher.getInstance(ALGORITHM_STR, "BC");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解密方法
     *
     * @param encryptedData 要解密的字符串
     * @param keyBytes      解密密钥
     * @param ivs           自定义对称解密算法初始向量 iv
     * @return 解密后的字节数组
     */
    private static byte[] decryptOfDiyIV(byte[] encryptedData, byte[] keyBytes, byte[] ivs) {
        byte[] encryptedText = null;
        init(keyBytes);
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivs));
            encryptedText = cipher.doFinal(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedText;
    }
}
