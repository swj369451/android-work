package com.qq149.android_work_sm_130.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qq149.android_work_sm_130.R;
import com.qq149.android_work_sm_130.home.bean.ResultBeanDate;
import com.qq149.android_work_sm_130.utils.Constants;

import java.util.List;

public class SeckillRecyclerAdapter extends RecyclerView.Adapter<SeckillRecyclerAdapter.ViewHolder> {
    private final List<ResultBeanDate.ResultBean.SeckillInfoBean.ListBean> list;
    private final Context mContext;

    public SeckillRecyclerAdapter(Context mContext, List<ResultBeanDate.ResultBean.SeckillInfoBean.ListBean> list) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = View.inflate(mContext, R.layout.item_seckill,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        //1.根据位置得到对应数据
        ResultBeanDate.ResultBean.SeckillInfoBean.ListBean listBean = list.get(i);
        //2.绑定数据
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+listBean.getFigure()).into(holder.iv_figure);
        holder.tv_cover_price.setText(listBean.getCover_price());
        holder.tv_origin_price.setText(listBean.getOrigin_price());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        //实例化
        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_figure = itemView.findViewById(R.id.iv_figure);
            tv_cover_price = itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price = itemView.findViewById(R.id.tv_origin_price);
        }
    }

}
