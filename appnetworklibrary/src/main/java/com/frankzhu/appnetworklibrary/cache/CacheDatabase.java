package com.frankzhu.appnetworklibrary.cache;

import com.frankzhu.appbaselibrary.app.FZBaseApplication;
import com.frankzhu.appbaselibrary.utils.FZFileHelper;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/5/11 下午5:50
 * Description: 缓存文件处理类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/5/11      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class CacheDatabase {
    private static CacheDatabase sCacheDatabase;

    private CacheDatabase() {

    }

    public static CacheDatabase getInstance() {
        if (sCacheDatabase == null) {
            sCacheDatabase = new CacheDatabase();
        }
        return sCacheDatabase;
    }

    /**
     * 删除缓存文件
     *
     * @param fileName 文件名
     */
    public void deleteCache(String fileName) {
        FZFileHelper.deleteFile(FZBaseApplication.getContext().getFilesDir() + "/" + fileName);
    }
}
