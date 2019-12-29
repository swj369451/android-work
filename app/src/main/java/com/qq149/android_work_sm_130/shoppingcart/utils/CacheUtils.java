package com.qq149.android_work_sm_130.shoppingcart.utils;

import android.content.Context;
import android.content.SharedPreferences;

class CacheUtils {
    /**
     * 得到保存的String类型的数据
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }

    /**
     * 保存string类型的数据
     * @param context
     *
     */
    public static void saveString(Context context, String key, String value){
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }

}
