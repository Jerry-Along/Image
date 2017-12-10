package com.along.image.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by mip on 2017/12/10.
 */

public class SDCardUtils {

    /**
     * 计算SD的可用空间
     */
    public static int getSDSpace(){
        int size = 0;
        //1. 指定SD的目录
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        //2. sd上分多少块区
        long blocksLong = statFs.getAvailableBlocksLong();
        //3. 每一个块区的大小
        long blockSizeLong = statFs.getBlockSizeLong();
        //4. 计算单位 KB--MB--GB
        size = (int) (blocksLong * blockSizeLong / 1024 / 1024);

        return size;
    }
    /**
     * 存到SD公共的目录
     */
    public static boolean savePublicDir(Context context,String pubType,String fileName,String data){
        FileOutputStream fos=null;
        if (Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)) {
            File publicDirectory = Environment.getExternalStoragePublicDirectory(pubType);
            File file = new File(publicDirectory, fileName);
            try {
                fos = new FileOutputStream(file);
                byte[] bytes = data.getBytes();
                fos.write(bytes,0,bytes.length);
                fos.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else {
            Toast.makeText(context,"哎呀，SD卡未找到！",Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }

    /**
     * 存到SD的自定义的文件夹
     */
    public static boolean saveCustomDir(Context context,String fileRoot,String fileName,String data){
        if (Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)) {
            FileOutputStream fos = null;
            File sdRootDir = Environment.getExternalStorageDirectory();
            File customDir = new File(sdRootDir, fileRoot);
            if (!customDir.exists()) {
                customDir.mkdir();
            }
            //在自定义的文件夹下操作
            File file = new File(customDir, fileName);
            try {
                fos = new FileOutputStream(file);
                byte[] bytes = data.getBytes();
                fos.write(bytes,0,bytes.length);
                fos.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            Toast.makeText(context,"哎呀，SD卡未找到！",Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }

    /**
     * 存放SD的私有缓存
     */
    public static boolean saveSdCach(Context context,String fileName,String data){
        FileOutputStream fos=null;
        if (Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)) {
            //1. 获取外部缓存的目录
            File cacheDir = context.getExternalCacheDir();
            File file = new File(cacheDir, fileName);
            try {
                fos=new FileOutputStream(file);
                byte[] bytes = data.getBytes();
                fos.write(bytes,0,bytes.length);
                fos.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            Toast.makeText(context,"哎呀，SD卡未找到！",Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }

    /**
     * 存放Sd私有缓存目录
     */
    public static boolean saveSdCachFile(Context context,String fileRoot,String fileName,String data){
        if (Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)) {
            FileOutputStream fos=null;
            File cacheDir = context.getExternalCacheDir();
            File fileDir = new File(cacheDir, fileRoot);
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }
            File file = new File(fileDir, fileName);

            try {
                fos=new FileOutputStream(file);
                byte[] bytes = data.getBytes();
                fos.write(bytes,0,bytes.length);
                fos.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else {
            Toast.makeText(context,"哎呀，SD卡未找到！",Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }


    //--------------------------SD卡的读取操作----------------------------------------
    public static String readFromSD(Context context,String filePath){
        if (Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)) {
            FileInputStream  fis=null;
            ByteArrayOutputStream baos=null;
            try {
                fis=new FileInputStream(filePath);
                baos=new ByteArrayOutputStream();

                try {
                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length=fis.read(bytes))!=-1){
                        baos.write(bytes,0,length);
                    }
                    return new String(baos.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }else {
            Toast.makeText(context,"哎呀，SD卡未找到！",Toast.LENGTH_SHORT).show();
            return null;
        }
        return null;
    }












}
