package com.along.image;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.along.image.utils.SDCardUtils;

/**
 * Created by mip on 2017/12/14.
 */

public class MyApp extends Application implements Handler.Callback {

    private static Context context;
    private static Handler mHandler;


    public static Context getContext() {
        return context;
    }

    public static Handler getMainThreadHandler() {
        return mHandler;
    }

    public static Thread getMainThread() {
        return Looper.getMainLooper().getThread();
    }

    public static long getMainThreadId() {
        return Looper.getMainLooper().getThread().getId();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        mHandler=new Handler(this);
        //初始化全局的工具类、框架
        SDCardUtils.init(this);

    }



    @Override
    public void onLowMemory() {
        super.onLowMemory();
        //当内存低的时候，辣鸡回收
        System.gc();
    }


    @Override
    public boolean handleMessage(Message msg) {

        return true;
    }
}
