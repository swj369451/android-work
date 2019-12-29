package com.qq149.android_work_sm_130.shoppingcart.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qq149.android_work_sm_130.R;
import com.qq149.android_work_sm_130.base.BaseFragment;
import com.qq149.android_work_sm_130.home.bean.GoodsBean;
import com.qq149.android_work_sm_130.shoppingcart.adapter.ShoppingCartAdapter;
import com.qq149.android_work_sm_130.shoppingcart.utils.CartStorage;

import java.util.List;


public class ShoppingCartFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = com.qq149.android_work_sm_130.shoppingcart.fragment.TypeFragment.class.getSimpleName();
    private TextView textView;
    private TextView tvShopcartEdit;
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;

    private ImageView ivEmpty;
    private TextView tvEmptyCartTobuy;
    private LinearLayout ll_empty_shopcart;
    private ShoppingCartAdapter adapter;

    @Override
    public void onResume() {
        super.onResume();
        initDate();
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2019-12-29 13:06:31 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */


    @Override
    public View initView() {
        Log.e(TAG,"购物车的fragment的UI被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_shopping_cart,null);
        tvShopcartEdit = (TextView)view.findViewById( R.id.tv_shopcart_edit );
        recyclerview = (RecyclerView)view.findViewById( R.id.recyclerview );
        llCheckAll = (LinearLayout)view.findViewById( R.id.ll_check_all );
        checkboxAll = (CheckBox)view.findViewById( R.id.checkbox_all );
        tvShopcartTotal = (TextView)view.findViewById( R.id.tv_shopcart_total );
        btnCheckOut = (Button)view.findViewById( R.id.btn_check_out );
        llDelete = (LinearLayout)view.findViewById( R.id.ll_delete );
        cbAll = (CheckBox)view.findViewById( R.id.cb_all );
        btnDelete = (Button)view.findViewById( R.id.btn_delete );
        btnCollection = (Button)view.findViewById( R.id.btn_collection );

        ll_empty_shopcart = view.findViewById(R.id.ll_empty_shopcart);
        ivEmpty = (ImageView)view.findViewById( R.id.iv_empty );
        tvEmptyCartTobuy = (TextView)view.findViewById( R.id.tv_empty_cart_tobuy );

        btnCheckOut.setOnClickListener( this );
        btnDelete.setOnClickListener( this );
        btnCollection.setOnClickListener( this );
        return view;
    }

    @Override
    public void onClick(View v) {
        if ( v == btnCheckOut ) {
            // Handle clicks for btnCheckOut
        } else if ( v == btnDelete ) {
            // Handle clicks for btnDelete
        } else if ( v == btnCollection ) {
            // Handle clicks for btnCollection
        }
    }


    @Override
    public void initDate() {
        super.initDate();
        Log.e(TAG,"购物车的fragment的数据被初始化了");
        showData();
    }

    /**
     * 显示数据
     */
    private void showData() {
        List<GoodsBean> goodsBeanList = CartStorage.getInstance().getAllData();

        if(goodsBeanList != null && goodsBeanList.size() > 0){
            //有数据
            //把当没有数据显示的布局-隐藏
            ll_empty_shopcart.setVisibility(View.GONE);
            //设置适配器
            adapter = new ShoppingCartAdapter(mContext,goodsBeanList,tvShopcartTotal,checkboxAll);
            recyclerview.setAdapter(adapter);

            //设置布局管理器
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        }else {
            //没有数据
            //显示数据为空的布局
            ll_empty_shopcart.setVisibility(View.VISIBLE);
        }

    }
}
