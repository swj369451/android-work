package com.qq149.android_work_sm_130.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.qq149.android_work_sm_130.R;

public class welcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //进入主页面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //执行主线程
                //启动主页面
                startActivity(new Intent(welcomeActivity.this, MainActivity.class));
            }
        },2000);
    }
}
