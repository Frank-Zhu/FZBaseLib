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
