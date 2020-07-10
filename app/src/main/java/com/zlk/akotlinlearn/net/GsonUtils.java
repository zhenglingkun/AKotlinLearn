package com.zlk.akotlinlearn.net;


import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.Reader;
import java.lang.reflect.Type;

public class GsonUtils {

    private static Gson gson = new Gson();

    public static <T> T fromJson(String json, Class<T> clazz) {
        return (T) fromJson(json, (Type) clazz);
    }

    public static <T> T fromJson(String json, Type type) {
        return (T) gson.fromJson(json, type);
    }

    public static <T> T fromJson(Reader reader, Class<T> clazz) {
        return (T) gson.fromJson(reader, clazz);
    }

    public static <T> T fromJson(Reader reader, Type type) {
        return (T) gson.fromJson(reader, type);
    }

    public static JsonElement toJson(Object object, Type typeOfSrc) {
        return gson.toJsonTree(object, typeOfSrc);
    }

    public static String toJson(Object o) {
        return gson.toJson(o);
    }

    public static <T> BaseBean<T> fromJsonObject(String reader, Class<T> clazz) {
        Type type = new ParameterizedTypeImpl(BaseBean.class, new Class[]{clazz});
        return gson.fromJson(reader, type);
    }

    public static <T> BaseListBean<T> fromJsonArray(String reader, Class<T> clazz) {
        Type type = new ParameterizedTypeImpl(BaseListBean.class, new Class[]{clazz});
        return gson.fromJson(reader, type);
    }

    public static <T> BasePageBean<T> fromJsonPage(String reader, Class<T> clazz) {
        Type type = new ParameterizedTypeImpl(BasePageBean.class, new Class[]{clazz});
        return gson.fromJson(reader, type);
    }

}
