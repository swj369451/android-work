package com.qq149.android_work_sm_130.app;

import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class MyApplication  extends Application {

    public static Context getmContext() {
        return mContext;
    }

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        initOKHttpClient();

    }

    private void initOKHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
