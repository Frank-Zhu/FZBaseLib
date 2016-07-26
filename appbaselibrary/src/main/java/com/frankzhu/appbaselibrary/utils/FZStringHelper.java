package com.frankzhu.appbaselibrary.utils;

import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/2/23  下午5:23.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/2/23        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class FZStringHelper {
    public static boolean isEmpty(String str) {
        return str == null || str.equals("null") || str.trim().equals("");
    }

    public static boolean notEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 获取 EditText 的内容
     *
     * @param edt EditText 控件
     * @return String内容
     */
    public static String getEditTextContent(EditText edt) {
        return edt == null ? "" : edt.getText().toString().trim();
    }

    /**
     * 是否 EditText 内容为空
     *
     * @param edt EditText控件
     * @return true 内容为空
     */
    public static boolean isEditTextEmpty(EditText edt) {
        return isEmpty(getEditTextContent(edt));
    }

    /**
     * 获取TextView 的内容
     *
     * @param tv TextView控件
     * @return String内容
     */
    public static String getTextViewContent(TextView tv) {
        return tv == null ? "" : tv.getText().toString().trim();
    }

    /**
     * 判断汉字
     *
     * @param content 内容
     * @return true 是汉字
     */
    public static boolean hasChinese(String content) {
        String regEx = "[\\u4e00-\\u9fa5]+$";   //"^[\\u2E80-\\uFE4F]+$";   //"[\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(content);
        return m.find();
    }

    /**
     * 设置 textView 控件的值
     *
     * @param text   需要设置的值
     * @param target textView
     */
    public static void setText(String text, TextView target) {
        if (isEmpty(text)) {
            target.setText("");
        } else {
            target.setText(text);
        }
    }

    /**
     * 设置 textView 控件的值 值为空时隐藏VIEW
     *
     * @param text   需要设置的值
     * @param target textView
     */
    public static void setTextEmptyGone(String text, TextView target) {
        if (isEmpty(text)) {
            FZUtils.setGone(target, true);
        } else {
            target.setText(text);
            FZUtils.setGone(target, false);
        }
    }

    /**
     * 设置 textView 控件的值 值为空时隐藏VIEW
     *
     * @param text   需要设置的值
     * @param target textView
     */
    public static void setTextEmptyInvisible(String text, TextView target) {
        if (isEmpty(text)) {
            FZUtils.setInvisible(target, true);
        } else {
            target.setText(text);
            FZUtils.setInvisible(target, false);
        }
    }


    /**
     * 字符串数组转字符串
     *
     * @param strings 需要转换的字符串数组
     * @return String
     */
    public static StringBuilder formatArrayStrings(String[] strings, int formatSize, String format) {
        StringBuilder buffer = new StringBuilder();
        if (strings != null && strings.length > 0) {
            int size = strings.length > formatSize ? formatSize : strings.length;
            for (int i = 0; i < size; i++) {
                buffer.append(strings[i]).append(format);
            }
        }
        return buffer;
    }
}
