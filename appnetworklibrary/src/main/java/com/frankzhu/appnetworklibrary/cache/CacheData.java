package com.frankzhu.appnetworklibrary.cache;

import com.frankzhu.appbaselibrary.app.FZBaseApplication;
import com.frankzhu.appbaselibrary.utils.FZFileHelper;
import com.frankzhu.appbaselibrary.utils.FZStringHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/5/13 下午6:20
 * Description: 缓存列表数据类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/5/13      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class CacheData<T> {
    private String mFileName;
    private Gson gson;
    private Class<T> className;

    /**
     * 获取List结构返回数据时的构造函数
     *
     * @param fileName  缓存文件名
     * @param className list数据结构类名
     */
    public CacheData(String fileName, Class<T> className) {
        mFileName = fileName;
        gson = new Gson();
        this.className = className;
    }

    /**
     * 获取普通数据结构返回数据时的构造函数
     *
     * @param fileName 缓存文件名
     */
    public CacheData(String fileName) {
        mFileName = fileName;
        gson = new Gson();
    }

    private File getCacheDataFile(String fileName) {
        return new File(FZBaseApplication.getContext().getFilesDir(), fileName);
    }

    /**
     * 缓存列表信息
     *
     * @param data   列表数据
     * @param append 是否追加
     */
    public void cacheList(ArrayList<T> data, boolean append) {
        if (data != null && data.size() > 0) {
            ArrayList<T> list = null;
            File file = getCacheDataFile(mFileName);
            if (!file.exists()) {
                try {
                    if (!file.createNewFile()) {
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            if (append) {//追加数据
                list = readCacheList();
                if (list != null) {
                    list.addAll(data);
                }
            }
            String json = gson.toJson(list == null ? data : list);
            FZFileHelper.writeInfoToFile(file, json, false);
        }
    }

    /**
     * 读取缓存的列表信息 这个对应的构造函数为 {@link CacheData#CacheData(String, Class)}
     *
     * @return ArrayList
     */
    public ArrayList<T> readCacheList() {
        File file = getCacheDataFile(mFileName);
        if (file.exists()) {
            String data = FZFileHelper.readFileToString(file);
            if (FZStringHelper.notEmpty(data)) {
                return fromJsonArray(data, className);
            }
        }
        return null;
    }

    /**
     * 读取list 泛型数据返回值
     *
     * @param json  数据
     * @param clazz list结构类名
     * @return ArrayList
     */
    public ArrayList<T> fromJsonArray(String json, Class<T> clazz) {
        ArrayList<T> list = new ArrayList<>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, clazz));
        }
        return list;
    }

    /**
     * 缓存信息
     *
     * @param data 缓存数据
     */
    public void cacheData(T data) {
        if (data != null) {
            File file = getCacheDataFile(mFileName);
            String json = gson.toJson(data);
            FZFileHelper.writeInfoToFile(file, json, false);
        }
    }

    /**
     * 读取缓存的信息
     *
     * @return T
     */
    public T readCacheData() {
        File file = getCacheDataFile(mFileName);
        if (file.exists()) {
            String data = FZFileHelper.readFileToString(file);
            if (FZStringHelper.notEmpty(data)) {
                return gson.fromJson(data, new TypeToken<T>() {
                }.getType());
            }
        }
        return null;
    }
}
