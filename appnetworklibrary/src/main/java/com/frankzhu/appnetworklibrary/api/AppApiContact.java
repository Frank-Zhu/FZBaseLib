package com.frankzhu.appnetworklibrary.api;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/4/30 下午4:35
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/4/30      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class AppApiContact {
    public static final int STATUS_NET_SUCCESS = 0;

    public static final String HTTP_INPUT_TYPE = "application/json";
    public static final String HTTP_FILE_TYPE = "multipart/form-data";
    public static final String ENCODE_TYPE = "UTF-8";

    public static final int DEFAULT_PAGE_SIZE = 20;//默认每页请求数据
    public static final int DEFAULT_CURRENT_PAGE = 1;//默认当前请求页
}
