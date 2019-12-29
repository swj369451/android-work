package com.qq149.android_work_sm_130.home.fragment;

import android.drm.ProcessedData;
import android.nfc.Tag;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.qq149.android_work_sm_130.R;
import com.qq149.android_work_sm_130.base.BaseFragment;
import com.qq149.android_work_sm_130.home.adapter.HomeFragmentAdapter;
import com.qq149.android_work_sm_130.home.bean.ResultBeanDate;
import com.qq149.android_work_sm_130.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;


public class HomeFragment extends BaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();

    private RecyclerView rvHome;
    private ImageView ib_top;
    private  TextView tv_search_home;
    private TextView tv_message_home;
    private HomeFragmentAdapter adapter;

    /**
     * 返回的数据
     */
    private ResultBeanDate.ResultBean resultBean;

    @Override
    public View initView() {
        Log.e(TAG,"主页面的fragment的UI被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_home,null);
        rvHome = view.findViewById(R.id.rv_home);
        ib_top = view.findViewById(R.id.ib_top);
        tv_search_home = view.findViewById(R.id.tv_search_home);
        tv_message_home = view.findViewById(R.id.tv_message_home);

        //设置点击事件
        initListener();
        return view;
    }



    @Override
    public void initDate() {
        super.initDate();
        Log.e(TAG,"主页面的fragment的数据被初始化了");
        //联网获取请求
        getDataFromNet();
    }

    private void getDataFromNet() {
        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback()
                {
                    //当请求失败的时候打印出来
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG,"首页请求失败"+e.getMessage());
                    }

                    //当联网成功的时候把数据返回来
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG,"首页请求成功");
                        processedData(response);
                    }

                });
    }

    private void processedData(String json) {
        ResultBeanDate resultBeanDate = JSON.parseObject(json, ResultBeanDate.class);
        resultBean = resultBeanDate.getResult();

        if(resultBean != null){
            //有数据
            //设置适配器
            adapter = new HomeFragmentAdapter(mContext,resultBean);
            rvHome.setAdapter(adapter);
            //设置监听
            GridLayoutManager manager = new GridLayoutManager(mContext, 1);
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(position<=3){
                        ib_top.setVisibility(View.GONE);
                    }else{
                        //显示
                        ib_top.setVisibility(View.VISIBLE);
                    }
                    //只能返回1
                    return 1;
                }
            });
            //设置布局管理器
            rvHome.setLayoutManager(manager);
        }else {
            //没有数据
        }


        System.out.println("解析成功"+resultBean.getHot_info().get(0).getName());
    }

    private void initListener() {
        //置顶的监听
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回到顶部
                rvHome.scrollToPosition(0);
            }
        });

        //搜索的监听
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"搜索",Toast.LENGTH_SHORT).show();
            }
        });

        //消息的监听
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"进入消息中心",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
