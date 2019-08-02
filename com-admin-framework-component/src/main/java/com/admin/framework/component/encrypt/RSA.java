package com.admin.framework.component.encrypt;

import lombok.Data;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 *  RSA加密工具类
 * @author ZSW
 * @date 2019/1/22
 */
public class RSA {

    private static final String CHART_SET = "UTF-8";
    private static final String INSTANCE = "RSA";

    public static void main(String[] args) {
        try {
            String str = "message";
            String priKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJNDjKEmGk7itpYDvdEGUB/wSONu3LCtCJemlaUCencXG+3PPBpok5cP+4tbRDhZka5b3nHowl0ab9cnkGuBJdkUjUOrLGYGv/cRG9XhpPXrwy1KB6msyz1AempmCEOVndl6ugNwSEnJ6ZbFncPXaWgDtdTKHyMMx97znr35rBIJAgMBAAECgYEAiF41C8VM91/Z1HEChV1jNTA9Tt6KXtDc9BG+4V66KS2tHzqYXZwZj/ms5g9NzSCkVuGby7/OvIiOqJ5qjiK5+C6dNHnbb1NmU899HSm58nyVOe3u3YCKK/TwUoNW8RvtrNZWR8mVH5fWQPDjwEX9lFjGwlPrQukRMlISsk/XUQECQQDm45E+Cb5lj6wkVa2XKMd8EoypIBsak7tPqqBad48F+lUqGC3vIDyLbcBU/vwQzjKau/bA4EKIpBDZ/G0uMrL9AkEAo0evOW+xWn/4oG+UDEtVJkr0tg9lmwaoWQlC+gt0s2o++6VItxif6yxLTqbOnnUIGHpToYT5Ifzy3rET+SBG/QJBAIWh0o9wfkUz2XMSL61cSdDyv3yne2hvdqsOW07hAeN41s/NPNC4/LHKLNZE2kkzvrA1+AP8CCYjpykzY1GowFUCQDQgHGZfq+TSWdMOC9to6UQSRdEhVTSKiY/zDvi4U4c7cePwPiiFqffv6hKv2eJoRi9aTfyQcOBS2k4RhejQTxkCQGbLjAMd6LgYBep7E3H0Gvdo7KKcxKolSjSI4rvCvr2aXs+UtkvN8edjMnQTACG6KyJouPDIoyiYJlO87V6LVYY=";
            String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCTQ4yhJhpO4raWA73RBlAf8EjjbtywrQiXppWlAnp3FxvtzzwaaJOXD/uLW0Q4WZGuW95x6MJdGm/XJ5BrgSXZFI1DqyxmBr/3ERvV4aT168MtSgeprMs9QHpqZghDlZ3ZeroDcEhJyemWxZ3D12loA7XUyh8jDMfe8569+awSCQIDAQAB";

//            Key key = genKeyPair();
//            System.out.println(key);
//            String privateKey = key.getPrivateKey();
//            String publicKey = key.getPublicKey();
            String encrypt = encrypt(str, pubKey);
            System.out.println(encrypt);
            String decrypt = decrypt(encrypt, priKey);
            System.out.println(decrypt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 随机生成密钥对
     * @throws NoSuchAlgorithmException
     */
    public static Key genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(INSTANCE);
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        Key key = new Key();
        key.setPrivateKey(privateKeyString);
        key.setPublicKey(publicKeyString);
        return key;
    }
    /**
     * RSA公钥加密
     *
     * @param str
     *            加密字符串
     * @param publicKey
     *            公钥
     * @return 密文
     * @throws Exception
     *             加密过程中的异常信息
     */
    public static String encrypt( String str, String publicKey ) throws Exception{
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(INSTANCE).generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance(INSTANCE);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes(CHART_SET)));
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str
     *            加密字符串
     * @param privateKey
     *            私钥
     * @return 铭文
     * @throws Exception
     *             解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception{
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes(CHART_SET));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

    @Data
    static class Key{
        private String publicKey;
        private String privateKey;
    }


}
