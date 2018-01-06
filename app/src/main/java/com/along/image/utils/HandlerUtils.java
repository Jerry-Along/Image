package com.along.image.utils;

import android.os.Handler;

import com.along.image.MyApp;

/**
 * Created by mip on 2018/1/2.
 */

public class HandlerUtils {

    public static final String TAG=HandlerUtils.class.getSimpleName();

    /**
     * 获取主线程的handler
     */
    public static Handler getHandler() {
        return MyApp.getMainThreadHandler();
    }

    /**
     * 延时在主线程执行runnable
     */
    public static boolean postDelayed(Runnable runnable, long delayMillis) {
        return getHandler().postDelayed(runnable, delayMillis);
    }

    /**
     * 在主线程执行runnable
     */
    public static boolean post(Runnable runnable) {
        return getHandler().post(runnable);
    }

    /**
     * 从主线程looper里面移除runnable
     */
    public static void removeCallbacks(Runnable runnable) {
        getHandler().removeCallbacks(runnable);
    }

    public static long getMainThreadId() {
        return MyApp.getMainThreadId();
    }
    /**
     * 判断当前的线程是不是在主线程
     *
     * @return
     */
    public static boolean isRunInMainThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }
    public static void runInMainThread(Runnable runnable) {
        if (isRunInMainThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

}
