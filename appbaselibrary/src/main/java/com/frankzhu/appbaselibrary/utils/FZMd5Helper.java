package com.frankzhu.appbaselibrary.utils;

import java.security.MessageDigest;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/1/27  下午2:31.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/1/27        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class FZMd5Helper {

    public static String encrypt(String password) {
        if (password == null)
            return null;
        try {
            byte[] bytes = password.getBytes("UTF8");
            return encrypt(bytes);

        } catch (Exception e) {
            return null;
        }
    }

    public static String encrypt(byte[] password) {
        if (password == null)
            return null;
        if (password.length == 0)
            return "";
        byte[] md5 = MD5(password);
        if (md5 == null)
            return null;
        return FZBase64Helper.encode(md5);
    }

    private static byte[] MD5(byte[] source) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(source);
            return md5.digest();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getMessageDigest(String buffer) {
        return getMessageDigest(buffer.getBytes());
    }

    public static String getMessageDigest(byte[] buffer) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(buffer);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
