package com.mrmo.mpaylib;

import com.mrmo.mpaylib.model.MPayModel;

/**
 * Created by moguangjian on 16/1/20 10:12.
 */
public interface MPayAble {

    /**
     * 支付监听
     *
     * @param mPayListener
     */
    void setOnPayListener(MPayListener mPayListener);

    /**
     * 设置支付参数
     *
     * @param mPayModel
     */
    void setPayParam(MPayModel mPayModel);

    /**
     * 开始支付
     */
    void pay();
}
