package com.qq149.android_work_sm_130.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qq149.android_work_sm_130.R;
import com.qq149.android_work_sm_130.home.bean.ResultBeanDate;
import com.qq149.android_work_sm_130.utils.Constants;

import java.util.List;

public class ChannelAdapter extends BaseAdapter {

    private final Context mContent;
    private final List<ResultBeanDate.ResultBean.ChannelInfoBean> datas;

    public ChannelAdapter(Context mContext, List<ResultBeanDate.ResultBean.ChannelInfoBean> channel_info) {
        this.mContent = mContext;
        this.datas = channel_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = View.inflate(mContent, R.layout.item_channel,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_icon = convertView.findViewById(R.id.iv_channel);
            viewHolder.tv_title = convertView.findViewById(R.id.tv_channel);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //根据位置得到对应数据
        ResultBeanDate.ResultBean.ChannelInfoBean channelInfoBean = datas.get(position);
        Glide.with(mContent).load(Constants.BASE_URL_IMAGE+channelInfoBean.getImage()).into(viewHolder.iv_icon);
        viewHolder.tv_title.setText(channelInfoBean.getChannel_name());
        return convertView;
    }

    static class ViewHolder{
        ImageView iv_icon;
        TextView tv_title;
    }
}
