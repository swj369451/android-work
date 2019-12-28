package com.qq149.android_work_sm_130.home.fragment;

import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qq149.android_work_sm_130.R;
import com.qq149.android_work_sm_130.base.BaseFragment;



public class HomeFragment extends BaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();

    private RecyclerView rvHome;
    private ImageView ib_top;
    private  TextView tv_search_home;
    private TextView tv_message_home;

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
