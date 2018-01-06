package com.along.image;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.provider.ContactsContract;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.along.image.utils.OkHttpNetUtils;
import com.along.image.utils.UiUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String string = UiUtils.getString(R.string.app_name);
    }

}
