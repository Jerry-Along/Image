package com.along.image.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.along.image.R;
import com.along.image.cache.RamCache;

import java.lang.ref.WeakReference;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Created by mip on 2017/12/15.
 * @author Along
 */

public class ImageLoadUtils {

    private static final String TAG = ImageLoadUtils.class.getSimpleName();
    private static final int LOAD_FAIL = 0;
    private static final int LOAD_SUCCEED = 1;
    private static final int CPU_CORE = Runtime.getRuntime().availableProcessors();


    public static void display(final ImageView container, final String url){
        Bitmap bitmap = RamCache.getInstane().get(url);
        if (bitmap != null) {
            container.setImageBitmap(bitmap);
            return;
        }
        /**
         * 加载图片
         * 1.设置一个占位图
         * 2.从网络上加载资源
         */
        container.setImageResource(R.mipmap.loading);
        container.setTag(url);

        final MyHandler handler=new MyHandler(container,url);


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //网络请求下载图片
                byte[] bytes = OkHttpNetUtils.getBytes(url);
                if (bytes != null) {
                    Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                    Message msg = Message.obtain();
                    msg.what=LOAD_SUCCEED;
                    msg.obj=decodeByteArray;
                    //如果成功添加到缓存中
                    RamCache.getInstane().put(url,decodeByteArray);
                    handler.sendMessage(msg);
                }else {
                    handler.sendEmptyMessage(LOAD_FAIL);
                }
            }
        };
        ThreadPoolExecutor executor = new ThreadPoolExecutor(CPU_CORE + 1,
                CPU_CORE * 2 + 1,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(128), new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable r) {

                return new Thread(r,"ImageLoadUtils");
            }
        });
        executor.execute(runnable);

    }

    public static  class MyHandler extends Handler{
        private final WeakReference<ImageLoadUtils> mUtils;
        private ImageView container;
        private String url;

        public MyHandler(ImageView container,String url) {
            this.mUtils = new WeakReference<ImageLoadUtils>(new ImageLoadUtils());
            this.container=container;
            this.url=url;
        }

        @Override
        public void handleMessage(Message msg) {
            ImageLoadUtils imageLoadUtils = mUtils.get();
            if (imageLoadUtils != null) {
                switch (msg.what) {
                    case LOAD_FAIL:
                        container.setImageResource(R.mipmap.error);
                        Log.e(TAG, "加载失败了！"+url);
                        break;
                    case LOAD_SUCCEED:
                        if (container.getTag().equals(url)) {
                            container.setImageBitmap((Bitmap) msg.obj);
                        }
                        break;
                    default:

                        break;
                }
            }

        }
    }



}
