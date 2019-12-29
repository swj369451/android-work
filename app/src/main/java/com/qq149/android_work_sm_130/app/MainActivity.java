package com.qq149.android_work_sm_130.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.qq149.android_work_sm_130.R;
import com.qq149.android_work_sm_130.base.BaseFragment;
import com.qq149.android_work_sm_130.community.fragment.CommunityFragment;
import com.qq149.android_work_sm_130.home.fragment.HomeFragment;
import com.qq149.android_work_sm_130.shoppingcart.fragment.ShoppingCartFragment;
import com.qq149.android_work_sm_130.shoppingcart.fragment.TypeFragment;
import com.qq149.android_work_sm_130.user.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {


    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    private RadioGroup rm_main;

    //多个fragment的实例集合
    private ArrayList<BaseFragment> fragments;

    private int position = 0;
    //缓存的fragment
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //butterknife和当前Activity自动绑定
        ButterKnife.bind(this);

        //初始化fragment
        initFragment();
        //设置RadioGroup的监听
        initLinstener();
    }

    private void initLinstener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home://主页
                        position = 0;
                        break;
                    case R.id.rb_type://分类
                        position = 1;
                        break;
                    case R.id.rb_community: //社区
                        position = 2;
                        break;
                    case R.id.rb_cart: //购物车
                        position = 3;
                        break;
                    case R.id.rb_user: //用户
                        position = 4;
                        break;
                    default:
                        position = 0;
                        break;
                }
                //根据位置不同取不同的fragment
                BaseFragment baseFragment =  getFragment(position);
                //第一个参数是上一次显示的
                //第二是现在所需要的
                switchFragment(tempFragment,baseFragment);
            }
        });
        rgMain.check(R.id.rb_home);

    }

    private  void  initFragment(){
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());
    }

    private  BaseFragment getFragment(int position){
        if (fragments != null && fragments.size()>0){
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }
    private void switchFragment(Fragment fromFragment,BaseFragment nextFragment){
        if(tempFragment != nextFragment){
            tempFragment = nextFragment;
            if(nextFragment != null){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextfragment是否添加
                if(!nextFragment.isAdded()){
                    //隐藏当前fragment
                    if(fromFragment != null){
                        transaction.hide(fromFragment);
                    }
                    //添加fragment
                    transaction.add(R.id.frameLayout,nextFragment).commit();
                }else{
                    //隐藏当前fragment
                    if(fromFragment != null){
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }

    }
}
