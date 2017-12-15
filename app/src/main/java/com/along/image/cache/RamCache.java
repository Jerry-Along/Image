package com.along.image.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by mip on 2017/12/14.
 */

public class RamCache implements ImageCache{

    public static RamCache mRamCache;

    private LruCache<String,Bitmap> mLruCache;

    public void RamCache(){
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
        mLruCache=new LruCache<String,Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //用来计算一个item的大小(每张图片)
//                int size = value.getHeight() * value.getRowBytes();
                int size = value.getByteCount();
                return size;
            }
        };
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mLruCache.put(url,bitmap);

    }

    @Override
    public Bitmap get(String url) {
        return mLruCache.get(url);
    }

    /**
     * 单例模式优化懒汉式
     * 双重判断锁，减少性能损失的问题
     * @return 返回实列
     */
    public static RamCache getInstane(){

        if (mRamCache != null) {
            synchronized (RamCache.class){
                if (mRamCache != null) {
                    mRamCache=new RamCache();
                }
            }
        }
        return mRamCache;
    }


}
