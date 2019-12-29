package com.qq149.android_work_sm_130.home.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.zhy.magicviewpager.transformer.RotateDownPageTransformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import java.util.logging.LogRecord;

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
        }else if(viewType==ACT){
            return new ActViewHolder(mContext,mLayoutInflater.inflate(R.layout.act_item,null));
        }else if(viewType==SECKILL){
            return new SeckillViewHolder(mContext,mLayoutInflater.inflate(R.layout.seckill_item,null));
        }else if(viewType==RECOMMEND){
            return new RecommendViewHolder(mContext,mLayoutInflater.inflate(R.layout.recommend_item,null));
        }else if(viewType==HOT){
            return new HotViewHolder(mContext,mLayoutInflater.inflate(R.layout.hot_item,null));
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
            ChannelViewHolder channelViewHolder = (ChannelViewHolder)holder;
            channelViewHolder.setData(resultBean.getChannel_info());
        }else if(getItemViewType(position)==ACT){
            ActViewHolder actViewHolder = (ActViewHolder)holder;
            actViewHolder.setData(resultBean.getAct_info());
        }else if(getItemViewType(position)==SECKILL){
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder)holder;
            seckillViewHolder.setData(resultBean.getSeckill_info());
        }else if(getItemViewType(position)==RECOMMEND){
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder)holder;
            recommendViewHolder.setData(resultBean.getRecommend_info());
        }else if(getItemViewType(position)==HOT){
             HotViewHolder hotViewHolder = (HotViewHolder)holder;
            hotViewHolder.setData(resultBean.getHot_info());
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
            case SECKILL:
                currentType =SECKILL;
                break;

        }
        return currentType;
    }

    //总共有多少个item
    @Override
    public int getItemCount() {
        return 6;
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
class ActViewHolder extends RecyclerView.ViewHolder{
    private Context mContext;
    private ViewPager act_viewpage;


    public ActViewHolder(Context mContext, View itemView) {
        super(itemView);
        this.mContext = mContext;
        act_viewpage = itemView.findViewById(R.id.act_viewpager);
    }

    public void setData(final List<ResultBeanDate.ResultBean.ActInfoBean> act_info) {

        //设置间距
        act_viewpage.setPageMargin(40);
        act_viewpage.setOffscreenPageLimit(3);

        //设置过度动画
        act_viewpage.setPageTransformer(true,new RotateDownPageTransformer());

        //1.有数据了
        //2.设置适配器
        act_viewpage.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return act_info.size();
            }

            //view页面，o是instantiateItem方法返回的值
            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view==o;
            }

            /**
             *
             * @param container viewpage
             * @param position 对应页面的位置
             * @return
             */
            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, final int position) {
                ImageView imageView = new ImageView(mContext);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                //添加到容器中
                Glide.with(mContext).load(Constants.BASE_URL_IMAGE + act_info.get(position).getIcon_url()).into(imageView);
                container.addView(imageView);
                
                //设置点击事件
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "position"+position, Toast.LENGTH_SHORT).show();
                    }
                });
                return imageView;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });
    }
}

class SeckillViewHolder extends RecyclerView.ViewHolder{

    private final Context mContext;
    private TextView tv_time_seckill;
    private TextView tv_more_seckill;
    private RecyclerView rv_seckill;
    private SeckillRecyclerAdapter adapter;

    //    相差多少时间
    private long dt = 0;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dt=dt-1000;
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            String time  = format.format(new Date(dt));
            tv_time_seckill.setText(time);
            handler.removeMessages(0);
            handler.sendEmptyMessageDelayed(0,1000);
            if(dt<=0){
                //把消息移除
                handler.removeCallbacksAndMessages(null);
            }
        }
    };
    public SeckillViewHolder(Context mContext, View itemView) {
        super(itemView);
        tv_time_seckill = itemView.findViewById(R.id.tv_time_seckill);
        tv_more_seckill = itemView.findViewById(R.id.tv_more_seckill);
        rv_seckill = itemView.findViewById(R.id.rv_seckill);
        this.mContext = mContext;
    }

    public void setData(ResultBeanDate.ResultBean.SeckillInfoBean seckill_info) {
        //1.得到数据
        //2.设置数据，文本和recycleView数据
        adapter = new SeckillRecyclerAdapter(mContext,seckill_info.getList());
        rv_seckill.setAdapter(adapter);

        //设置布局管理器
        rv_seckill.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
//        设置item的点击事件
        adapter.setOnSeckillRecyclerView(new SeckillRecyclerAdapter.onSeckillRecyclerView() {
            @Override
            public void onItem(int position) {
                Toast.makeText(mContext, "秒杀"+position, Toast.LENGTH_SHORT).show();
            }
        });

//        秒杀倒计时
        dt=Integer.valueOf(seckill_info.getEnd_time()) -Integer.valueOf(seckill_info.getStart_time());


        handler.sendEmptyMessageDelayed(0,1000);

    }
}

class RecommendViewHolder extends RecyclerView.ViewHolder{

    private final Context mContext;
    private TextView tv_more_recommend;
    private GridView gv_recommend;
    private RecommendGridViewAdapter recommendGridViewAdapter;

    public RecommendViewHolder(final Context mContext, View itemView) {
        super(itemView);
        this.mContext = mContext;
        tv_more_recommend = itemView.findViewById(R.id.tv_more_recommend);
        gv_recommend = itemView.findViewById(R.id.gv_recommend);

        //设置点击事件
        gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setData(List<ResultBeanDate.ResultBean.RecommendInfoBean> recommend_info) {
        //1.有数据
        //2.设置适配器
        recommendGridViewAdapter = new RecommendGridViewAdapter(mContext,recommend_info);
        gv_recommend.setAdapter(recommendGridViewAdapter);
    }
}

class HotViewHolder extends  RecyclerView.ViewHolder{

    private final Context mContext;

    private TextView tv_more_hot;
    private GridView gv_hot;
    private  HotGridViewAdapter adapter;

    public HotViewHolder(final Context mContext, View itemView) {
        super(itemView);
        this.mContext = mContext;
        tv_more_hot = itemView.findViewById(R.id.tv_more_hot);
        gv_hot = itemView.findViewById(R.id.gv_hot);
        //设置item的监听
        gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setData(List<ResultBeanDate.ResultBean.HotInfoBean> hot_info) {
        //1.有数据
        //2.设置GridView的适配器
        adapter = new HotGridViewAdapter(mContext,hot_info);
        gv_hot.setAdapter(adapter);
    }
}

