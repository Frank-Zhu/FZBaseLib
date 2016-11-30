package com.frankzhu.appnetworklibrary.param.base;

import com.google.gson.Gson;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/4/29 下午4:36
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/4/29      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class BaseParam {
    public String toJson() {
        return new Gson().toJson(this);
    }
}
