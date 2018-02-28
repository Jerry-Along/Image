package com.along.image.utils;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by mip on 2018/2/28.
 */

public class TypeUtils {

    public static <T> T getObjectType(Object o,int index){
        T t = null;
        try {
            t = ((Class<T>) ((ParameterizedType) o.getClass().getGenericSuperclass()).getActualTypeArguments()[index]).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

}
