package com.frankzhu.appnetworklibrary.param.base;

import com.frankzhu.appnetworklibrary.api.AppApiContact;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/5/12 上午10:44
 * Description: 分页请求参数基础类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/5/12      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class BasePageParam extends BaseParam {
    public int currentPage = AppApiContact.DEFAULT_CURRENT_PAGE;
    public int pageSize = AppApiContact.DEFAULT_PAGE_SIZE;
}
