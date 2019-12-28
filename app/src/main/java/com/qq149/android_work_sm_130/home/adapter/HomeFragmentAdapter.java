package com.qq149.android_work_sm_130.home.adapter;

import android.content.Context;
import android.media.SoundPool;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qq149.android_work_sm_130.R;
import com.qq149.android_work_sm_130.home.bean.ResultBeanDate;
import com.qq149.android_work_sm_130.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;

import java.util.ArrayList;
import java.util.List;

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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if(viewType == BANNER){
            return new BannerViewHolder(mContext,mLayoutInflater.inflate(R.layout.banner_viewpager,null));
        }else if(viewType==CHANNEL){
            return new ChannelViewHolder(mContext,mLayoutInflater.inflate(R.layout.channel_item,null));
        }
        return null;
    }


    class BannerViewHolder extends RecyclerView.ViewHolder{

        private Context mContext;
        private Banner banner;
        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            this.banner = itemView.findViewById(R.id.banner);
        }

        public void setDate(List<ResultBeanDate.ResultBean.BannerInfoBean> banner_info) {
            //设置banner的数据

            //得到图片集合地址
            List<String> imagesUrl = new ArrayList<>();
            for(int i = 0;i<banner_info.size();i++){
                String imageUrl = banner_info.get(i).getImage();
                imagesUrl.add(imageUrl);
            }

            //设置循环指示点
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

            //设置手风琴效果
            banner.setBannerAnimation(Transformer.Accordion);
            banner.setImages(imagesUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {

                    //网络请求图片
                    Glide.with(mContext).load(Constants.BASE_URL_IMAGE+url).into(view);
                }
            });

            //设置item的点击事件
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext,"position=="+position,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //相当于绑定数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==BANNER){
            BannerViewHolder bannerViewHolder = (BannerViewHolder)holder;
            bannerViewHolder.setDate(resultBean.getBanner_info());
        }else if(getItemViewType(position)==CHANNEL){
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(resultBean.getChannel_info());
        }
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
        return 2;
    }
}
class ChannelViewHolder extends RecyclerView.ViewHolder{

    private Context mContext;
    private GridView gv_channel;
    private ChannelAdapter adapter;

    public ChannelViewHolder(final Context mContext, View itemView) {
        super(itemView);
        this.mContext = mContext;
        gv_channel = itemView.findViewById(R.id.gv_channel);

        //设置item的点击事件
        gv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext,"position"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setData(List<ResultBeanDate.ResultBean.ChannelInfoBean> channel_info) {
        //得到数据
        //设置Gridview的适配器
        adapter = new ChannelAdapter(mContext,channel_info);
        gv_channel.setAdapter(adapter);
    }
}
