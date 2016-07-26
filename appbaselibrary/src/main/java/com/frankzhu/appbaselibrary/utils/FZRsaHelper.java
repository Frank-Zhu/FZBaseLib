package com.frankzhu.appbaselibrary.utils;

import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/5/3 下午3:07
 * Description: Rsa加密帮助类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/5/3      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class FZRsaHelper {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String TRANS_FORMATION = "RSA/ECB/PKCS1Padding";

    /**
     * 加密<br>
     * 用公钥加密
     *
     * @param data 需要加密的字节数据
     * @param key  加密key
     * @return byte[]
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {
        // 对公钥解密
        byte[] keyBytes = FZBase64Helper.decode(key);

        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(TRANS_FORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }
}
