package com.along.image.utils;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by mip on 2017/12/8.
 */

public class OkHttpNetUtils {

    public static Response getResponse(String requestUrl){
        Response response = null;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(requestUrl)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            response = call.execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static String getString(String requestUrl){

        Response response = getResponse(requestUrl);
        if (response.isSuccessful()) {
            try {
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static byte[] getBytes(String requestUrl){
        Response response = getResponse(requestUrl);
        if (response.isSuccessful()) {
            try {
                return response.body().bytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 异步加载回调函数
     */
    private static final int CPU_CORE = Runtime.getRuntime().availableProcessors();
    private static final int REQUEST_FAIL = 0;
    private static final int REQUEST_SUCCEED = 1;

    public static void getStringAsync(final String url, RequestCallBack callBack){
        final MyHandler handler = new MyHandler(callBack);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(CPU_CORE + 1,
                CPU_CORE * 2 + 1,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(128), new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable r) {

                return new Thread(r,"OkHttpNetUtils");
            }
        });
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                String result = getString(url);
                if (result != null) {
                    Message msg = Message.obtain();
                    msg.what=REQUEST_SUCCEED;
                    msg.obj=result;
                    handler.sendMessage(msg);
                }else {
                    handler.sendEmptyMessage(REQUEST_FAIL);
                }
            }
        };
        executor.execute(runnable);
    }

    public static  class MyHandler extends Handler {

        private final WeakReference<OkHttpNetUtils> mUtils;
        private RequestCallBack callBack;

        public MyHandler(RequestCallBack callBack) {
            this.mUtils = new WeakReference<OkHttpNetUtils>(new OkHttpNetUtils());
            this.callBack=callBack;
        }

        @Override
        public void handleMessage(Message msg) {
            OkHttpNetUtils okHttpNetUtils = mUtils.get();
            if (okHttpNetUtils != null) {
                callBack.onPrePare();
                switch (msg.what) {
                    case REQUEST_SUCCEED:
                        if (callBack != null) {
                            callBack.onSucceed(((String) msg.obj));
                        }
                        break;
                    case REQUEST_FAIL:
                        if (callBack != null) {
                            callBack.onFailure("网络异常，数据加载失败!");
                        }
                        break;
                    default:
                        break;
                }
                callBack.onLoadFinish();
            }

        }
    }

    public interface RequestCallBack{
        /**
         * 请求成功
         */
        void onSucceed(String result);
        /**
         * 请求失败
         */
        void onFailure(String message);
        /**
         * 准备请求，通常用来加载进度条
         */
        void onPrePare();
        /**
         * 加载结束
         */
        void onLoadFinish();
    }

}
