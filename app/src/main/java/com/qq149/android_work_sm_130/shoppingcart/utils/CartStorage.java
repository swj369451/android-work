package com.qq149.android_work_sm_130.shoppingcart.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.TextureView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qq149.android_work_sm_130.app.MyApplication;
import com.qq149.android_work_sm_130.home.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;

public class CartStorage {
    private static final String JSON_CART = "json_cart";
    private static  CartStorage instance;
    private final Context mContext;
    //SparseArray的性能优于HashMap
    private SparseArray<GoodsBean> sparseArray;

    private CartStorage(Context context){
        this.mContext = context;
        //把之前存储的数据读取
        sparseArray = new SparseArray<>(100);
        listToSparseArray();
    }

    /**
     * 从本地读取的数据加入到sparseArray中
     */
    private void listToSparseArray() {
        List<GoodsBean> goodsBeanList = getAllData();

        //把list数据转换成SparseArray
        for(int i = 0;i<goodsBeanList.size();i++){
            GoodsBean goodsBean = goodsBeanList.get(i);
            sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()),goodsBean);
        }
    }

    /**
     * 获取本地所有的数据
     * @return
     */
    public List<GoodsBean> getAllData() {
        ArrayList<GoodsBean> goodsBeansList = new ArrayList<>();
        //1.从本地获取
        String json = CacheUtils.getString(mContext,JSON_CART);
        //2.使用Gson转换成列表
        //判断不为空的时候
        if(!TextUtils.isEmpty(json)){
            goodsBeansList = new Gson().fromJson(json,new TypeToken<List<GoodsBean>>(){}.getType());
        }
        return goodsBeansList;
    }

    public static CartStorage getInstance(){
        if(instance == null){
            instance = new CartStorage(MyApplication.getmContext());
        }
        return instance;
    }

    /**
     * 添加数据
     * @param goodsBean
     */
    public void addData(GoodsBean goodsBean){
        //1.添加到内存中SparseArray
        //如果当前数据已经存在，旧修改成number递增
        GoodsBean tempData = sparseArray.get(Integer.parseInt(goodsBean.getProduct_id()));
        if(tempData != null){
            //内存中有了这条数据
            tempData.setName(tempData.getName()+1);
        }else{
            tempData = goodsBean;
            tempData.setNumber(1);
        }

        //同步到内存中去
        sparseArray.put(Integer.parseInt(tempData.getProduct_id()),tempData);

        //2.同步到本地
        saveLocal();
    }


    private void saveLocal(){
        //1.SparseArray转换成list
       List<GoodsBean> goodsBeanList = sparseToList();
       //2.使用Gson把列表转换成String类型
        String json = new Gson().toJson(goodsBeanList);
        //3.把string数据保存
        CacheUtils.saveString(mContext,JSON_CART,json);
    }

    private List<GoodsBean> sparseToList() {
        List<GoodsBean> goodsBeanList = new ArrayList<>();
        for(int i = 0;i<sparseArray.size();i++){
            GoodsBean goodsBean =sparseArray.valueAt(i);
            goodsBeanList.add(goodsBean);
        }
        return goodsBeanList;
    }

    public void updateData(GoodsBean goodsBean) {
        //1.内存中更新
        sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()),goodsBean);

        //2.同步到本地
        saveLocal();
    }

    public void deleteData(GoodsBean goodsBean) {
        //1.内存中删除
        sparseArray.delete(Integer.parseInt(goodsBean.getProduct_id()));

        //2.同步到本地
        saveLocal();
    }
}
