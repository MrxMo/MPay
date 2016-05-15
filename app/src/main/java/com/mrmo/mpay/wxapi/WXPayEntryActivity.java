package com.mrmo.mpay.wxapi;

import android.os.Bundle;

import com.mrmo.mpaylib.MWeChatPayResultActivity;

/**
 * 微信支付回调类
 * Created by moguangjian on 16/1/28 11:35.
 */
public class WXPayEntryActivity extends MWeChatPayResultActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPaySuccess(String info) {
    }

    @Override
    protected void onPayFailure(String info) {
    }

    @Override
    protected void onPayCancel(String info) {
    }

}