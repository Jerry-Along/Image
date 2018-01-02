package com.along.image.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by mip on 2017/12/20.
 */

public class ImageCutUtils {


    public static Bitmap sample2Bitmap(byte[] bytes,int exceptWidth,int exceptHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        //设置解码读取边缘
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeByteArray(bytes,0,bytes.length, options);
///       BitmapFactory.decodeFile(path,options);
        /**
         *         配置选项进行图片的解码
         */
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        int max = Math.max(exceptWidth / outWidth, exceptHeight / outHeight);
        /**
         * 小于1 会当做1来使用
         * 大于1 2的n次幂值，向下取整
         */
        options.inSampleSize=max;
        //关闭解码边缘
        options.inJustDecodeBounds=false;
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length, options);
    }



}
