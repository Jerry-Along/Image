package com.along.image.utils;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by mip on 2017/12/21.
 * @author Along
 */

public class ServiceUtils extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new TestBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    public class TestBinder extends Binder{
        /**
         * 1、提供公共的方法，可以获取services对象
         * 2、公共方法可以调用Service对象中的方法
         */
        public ServiceUtils getService(){
            return ServiceUtils.this;
        }
    }


}
