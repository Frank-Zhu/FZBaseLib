package com.frankzhu.appnetworklibrary.bean.base;

import com.google.gson.Gson;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/4/19 下午1:52
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/4/19      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class BaseBean {
    public String toJson() {
        return new Gson().toJson(this);
    }
}
