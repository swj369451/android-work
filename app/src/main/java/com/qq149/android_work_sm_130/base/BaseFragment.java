package com.qq149.android_work_sm_130.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 基类
 */
public abstract class BaseFragment extends Fragment {

    protected Context mContext;

    /**
     * 该类被创建的还是被回调
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 抽象类给子类实现,实现方法不同
     * @return
     */
    public abstract View initView();

    /**
     * 当activity被创建的时候回到这个方法
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDate();
    }

    /**
     * 当子类需要联网请求的时候，可以重写该方法，在该方法中请求联网
     */
    public  void initDate(){

    }
}
