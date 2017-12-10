package com.along.image.utils;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

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

}
