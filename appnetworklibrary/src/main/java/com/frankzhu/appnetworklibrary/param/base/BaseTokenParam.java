package com.frankzhu.appnetworklibrary.param.base;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/5/9 下午5:13
 * Description: 用户token参数基类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/5/9      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class BaseTokenParam extends BaseParam {
    public String token;

    public BaseTokenParam() {

    }

    public BaseTokenParam(String token) {
        this.token = token;
    }
}
