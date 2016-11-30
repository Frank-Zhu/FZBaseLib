package com.frankzhu.appnetworklibrary.param.base;

import com.frankzhu.appnetworklibrary.api.AppApiContact;

/**
 * Author:    CaoKang
 * Version    V1.0
 * Date:      2016/8/1  14:40.
 * Description:分页请求带Token参数基础类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2016/8/1        CaoKang           1.0                    1.0
 * Why & What is modified:
 */
public class BasePageTokenParam extends BaseTokenParam {
    public int currentPage = AppApiContact.DEFAULT_CURRENT_PAGE;
    public int pageSize = AppApiContact.DEFAULT_PAGE_SIZE;

    public BasePageTokenParam(String token) {
        this.token = token;
    }
}
