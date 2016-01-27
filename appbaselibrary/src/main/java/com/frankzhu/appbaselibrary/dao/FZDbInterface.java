package com.frankzhu.appbaselibrary.dao;

import android.content.ContentValues;

import java.util.List;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/1/27  下午2:14.
 * Description: 数据库接口操作函数
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/1/27        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public interface FZDbInterface<T> {
    /**
     * 查询某一条数据记录
     *
     * @param id 数据ID
     * @return T 返回查询到的第一条记录
     */
    T query(String id);

    /**
     * 删除所有数据
     *
     * @return count 本次操作的条数
     */
    int clearAll();

    /**
     * 批量插入数据
     *
     * @param list 需要插入的数据列表
     */
    void bulkInsert(List<T> list);

    ContentValues getContentValues(T data);

    List<ContentValues> getContentListValues(List<T> list);
}
