package com.qq149.android_work_sm_130.shoppingcart.adapter;

import android.content.Context;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qq149.android_work_sm_130.R;
import com.qq149.android_work_sm_130.home.bean.GoodsBean;
import com.qq149.android_work_sm_130.shoppingcart.utils.CartStorage;
import com.qq149.android_work_sm_130.shoppingcart.view.AddSubView;
import com.qq149.android_work_sm_130.utils.Constants;

import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {
    private final Context mContext;
    private final List<GoodsBean> goodBeanList;
    private final TextView tvShopcartTotal;
    private final CheckBox checkboxAll;
    //完成状态下的删除
    private final CheckBox cbAll;


    public ShoppingCartAdapter(Context mContext, List<GoodsBean> goodsBeanList, TextView tvShopcartTotal, CheckBox checkboxAll, CheckBox cbAll) {
        this.mContext = mContext;
        this.goodBeanList = goodsBeanList;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        this.cbAll = cbAll;
        showTotalPrice();
        //设置点击事件
        setListener();
        //校验是否全选
        checkAll();
    }

    private void setListener() {
        setOnItenClickListener(new onItenClickListener() {
            @Override
            public void onItemClick(int position) {
                //1.根据位置找到对应的Bean对象
                GoodsBean goodsBean = goodBeanList.get(position);
                //2.设置取反状态
                goodsBean.setSelected(!goodsBean.isSelected());
                //3.刷新状态
                notifyItemChanged(position);
                //4.校验是否全选
                checkAll();
                //5.重新计算价格
                showTotalPrice();
            }
        });
        //CheckBox的点击事件
        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到状态
                boolean isCheck = checkboxAll.isChecked();

                //根据状态设置全选和非全选
                checkAll_none(isCheck);

                //计算总价格
                showTotalPrice();
            }
        });

        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到状态
                boolean isCheck = cbAll.isChecked();

                //根据状态设置全选和非全选
                checkAll_none(isCheck);

            }
        });
    }

    /**
     * 设置全选和非全选
     * @param isCheck
     */
    public void checkAll_none(boolean isCheck) {
        if(goodBeanList != null && goodBeanList.size() > 0){
            for(int i = 0; i<goodBeanList.size(); i++){
                GoodsBean goodsBean = goodBeanList.get(i);
                goodsBean.setSelected(isCheck);
                notifyItemChanged(i);
            }
        }
    }

    public void checkAll() {
        if(goodBeanList != null && goodBeanList.size() > 0){
            int number = 0 ;
            for(int i = 0; i<goodBeanList.size(); i++){
                GoodsBean goodsBean = goodBeanList.get(i);
                if(!goodsBean.isSelected()){
                    //非全选
                    checkboxAll.setChecked(false);
                    cbAll.setChecked(false);
                }else{
                    //选中的
                    number++;

                }
            }
            if(number == goodBeanList.size()){
                //全选
                checkboxAll.setChecked(true);
                cbAll.setChecked(true);
            }
        }else {
            checkboxAll.setChecked(false);
            cbAll.setChecked(false);
        }
    }

    public void showTotalPrice() {
        tvShopcartTotal.setText("合计："+getTotalPrice());
    }

    /**
     * 计算总价格
     * @return
     */
    private double getTotalPrice() {
        double totalPrice = 0.0;
        if(goodBeanList != null && goodBeanList.size() > 0){
            for(int i = 0; i < goodBeanList.size(); i++){
                GoodsBean goodsBean = goodBeanList.get(i);
                if(goodsBean.isSelected()){
                    totalPrice = totalPrice + Double.valueOf(goodsBean.getNumber()) * Double.valueOf(goodsBean.getConver_price());
                }
            }
        }
        return totalPrice;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = View.inflate(mContext, R.layout.item_shop_cart,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //1.根据位置得到对应的Bean对象
        final GoodsBean goodsBean = goodBeanList.get(position);
        //2.设置数据
        holder.cb_gov.setChecked(goodsBean.isSelected());
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+goodsBean.getFigure()).into(holder.iv_gov);
        holder.tv_desc_gov.setText(goodsBean.getName());
        holder.tv_price_gov.setText("￥"+goodsBean.getConver_price());
        holder.ddSubView.setValue(goodsBean.getNumber());
        //设置最多和最少个数
        holder.ddSubView.setMinValue(1);
        holder.ddSubView.setMaxValue(8);

        //设置商品数量的变化
        holder.ddSubView.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void onNumberChange(int value) {
                //1.当前列表内存中
                goodsBean.setNumber(value);
                //2.本地要更新
                CartStorage.getInstance().updateData(goodsBean);
                //3.刷新适配器
                notifyItemChanged(position);
                //4.再次计算价格
                showTotalPrice();
            }
        });
    }

    @Override
    public int getItemCount() {
        return goodBeanList.size();
    }

    public void deleteDate() {
        if(goodBeanList != null && goodBeanList.size() > 0){
            for(int i = 0; i<goodBeanList.size(); i++){
                //删除选中的
                GoodsBean goodsBean = goodBeanList.get(i);
                if(goodsBean.isSelected()){
                    //内存-把移除
                    goodBeanList.remove(goodsBean);
                    //保存到本地
                    CartStorage.getInstance().deleteData(goodsBean);
                    //刷新
                    notifyItemRemoved(i);
                    i--;
                }
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private CheckBox cb_gov;
        private ImageView iv_gov;
        private TextView tv_desc_gov;
        private TextView tv_price_gov;
        private AddSubView ddSubView;

        public  ViewHolder (View itemView){
            super(itemView);
            cb_gov = itemView.findViewById(R.id.cb_gov);
            iv_gov = itemView.findViewById(R.id.iv_gov);
            tv_desc_gov = itemView.findViewById(R.id.tv_desc_gov);
            tv_price_gov = itemView.findViewById(R.id.tv_price_gov);
            ddSubView = itemView.findViewById(R.id.ddSubView);

            //设置item的点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItenClickListener != null){
                        onItenClickListener.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }
    /**
     * 点击item的监听
     */
    public interface  onItenClickListener{
        /**
         * 当点击某条的时候被回调
         */
        public void onItemClick(int position);
    }

    /**
     * 设置一个监听
     * @param onItenClickListener
     */
    public void setOnItenClickListener(ShoppingCartAdapter.onItenClickListener onItenClickListener) {
        this.onItenClickListener = onItenClickListener;
    }

    private onItenClickListener onItenClickListener;
}
