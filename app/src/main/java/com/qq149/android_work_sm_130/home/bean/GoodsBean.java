package com.qq149.android_work_sm_130.home.bean;

import java.io.Serializable;

public class GoodsBean implements Serializable {
    //价格
    private String conver_price;
    //图片
    private String figure;
    //名称
    private String name;
    //产品id
    private String product_id;

    private int number = 1;

    /**
     * 是否被选中
     */
    private boolean isSelected = true;

    public int getNumber() {
        return number;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getConver_price() {
        return conver_price;
    }

    public void setConver_price(String conver_price) {
        this.conver_price = conver_price;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "conver_price='" + conver_price + '\'' +
                ", figure='" + figure + '\'' +
                ", name='" + name + '\'' +
                ", product_id='" + product_id + '\'' +
                ", number=" + number +
                ", isSelected=" + isSelected +
                '}';
    }
}
