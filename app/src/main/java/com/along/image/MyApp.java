package com.along.image;

import android.app.Application;

/**
 * Created by mip on 2017/12/14.
 */

public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化全局的工具类、框架

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        //当内存低的时候，辣鸡回收
        System.gc();
    }
}
