package com.along.image;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.along.image.utils.OkHttpNetUtils;

public class MainActivity extends AppCompatActivity implements OkHttpNetUtils.RequestCallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpNetUtils.getStringAsync("",this);
    }

    @Override
    public void onSucceed(String result) {

    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onPrePare() {

    }

    @Override
    public void onLoadFinish() {

    }
}
