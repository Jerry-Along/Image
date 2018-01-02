package com.along.image.broad;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.along.image.R;

/**
 * Created by mip on 2018/1/2.
 * @author Along
 * 发送广播这个操作是Context中的一个方法,意味着持有Context对象的类都可以进行广播的发送
 *      Activity 就是Context实现
 *      Services 就是Context实现
 *      ContentProvider 虽然不是Context的实现，但是它也是持有Context的，可以很放便获取到
 *      BroadcastReceiver 不持有Context，但是在它活跃的时候会传递一个Context过来
 *
 */

    /**
     * 如果广播中需要进行耗时操作,我们要开启一个service进行
    <receiver
    android:name=".receivers.TeachSmsBroadcast"
    android:exported="true">
    <intent-filter>
     注册短信接收的action
    <action android:name="android.provider.Telephony.SMS_RECEIVED"/>
    </intent-filter>
    </receiver>*/


public class BroadCastTest extends BroadcastReceiver{
    private String TAG=BroadCastTest.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "onReceive: " );
        //启动一个前台的服务
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            Notification.Builder builder = new Notification.Builder(context,null);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle("新年快乐!");
            Notification notification = builder.build();
            notificationManagerCompat.notify(1, notification);
        }

        //有序广播停止发送
//        abortBroadcast();
        //向后(优先级低的广播)传递数据
        setResultData("新年快乐!");
    }
}
