package com.qq149.android_work_sm_130.user.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.qq149.android_work_sm_130.base.BaseFragment;


public class UserFragment extends BaseFragment {
    private static final String TAG = UserFragment.class.getSimpleName();
    private TextView textView;

    @Override
    public View initView() {
        Log.e(TAG,"用户的fragment的UI被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initDate() {
        super.initDate();
        Log.e(TAG,"用户的fragment的数据被初始化了");
        textView.setText("用户的内容");
    }
}
