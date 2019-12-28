package com.qq149.android_work_sm_130.shoppingcart.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.qq149.android_work_sm_130.base.BaseFragment;


public class ShoppingCartFragment extends BaseFragment {
    private static final String TAG = com.qq149.android_work_sm_130.shoppingcart.fragment.TypeFragment.class.getSimpleName();
    private TextView textView;

    @Override
    public View initView() {
        Log.e(TAG,"购物车的fragment的UI被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initDate() {
        super.initDate();
        Log.e(TAG,"购物车的fragment的数据被初始化了");
        textView.setText("购物车内容");
    }
}
