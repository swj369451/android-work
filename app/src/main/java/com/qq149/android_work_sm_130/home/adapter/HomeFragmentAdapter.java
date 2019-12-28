package com.qq149.android_work_sm_130.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qq149.android_work_sm_130.home.bean.ResultBeanDate;

public class HomeFragmentAdapter  extends RecyclerView.Adapter {

    //广告条幅类型
    public static  final  int BANNER = 0;
    //频道
    public static  final  int CHANNEL = 1;
    //活动类型
    public static  final  int ACT = 2;
    //秒杀类型
    public static  final  int SECKILL = 3;
    //推荐类型
    public static  final  int RECOMMEND = 4;
    //热卖类型
    public static  final  int HOT = 5;
    private final LayoutInflater mLayoutInflater;

    private  Context mContext;
    private  ResultBeanDate.ResultBean resultBean;
    //当前类型
    private  int currentType = BANNER;


    public HomeFragmentAdapter(Context mContext, ResultBeanDate.ResultBean resultBean) {
        this.mContext = mContext;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    //用于创建viewHolder，相当于创建ViewHolder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    //相当于绑定数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    //得到类型
    @Override
    public int getItemViewType(int position) {
        switch (position){
            case BANNER:
                currentType =BANNER;
                break;
            case CHANNEL:
                currentType =CHANNEL;
                break;
            case ACT:
                currentType =ACT;
                break;
            case RECOMMEND:
                currentType =RECOMMEND;
                break;
            case HOT:
                currentType =HOT;
                break;

        }
        return currentType;
    }

    //总共有多少个item
    @Override
    public int getItemCount() {
        return 1;
    }
}
