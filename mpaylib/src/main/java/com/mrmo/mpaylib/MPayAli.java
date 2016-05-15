package com.mrmo.mpaylib;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.mrmo.mpaylib.model.MPayAliModel;
import com.mrmo.mpaylib.model.MPayModel;
import com.mrmo.mpaylib.sources.ali.PayResult;
import com.mrmo.mpaylib.util.MAliPayInfoUtil;

/**
 * Created by moguangjian on 16/1/20 10:46.
 */
public class MPayAli implements MPayAble {

    private static final String TAG = MPayAli.class.getSimpleName();
    private Context context;
    private MPayListener mPayListener;
    private MPayModel mPayModel;

    public MPayAli(Context context) {
        this.context = context;
    }

    @Override
    public void setOnPayListener(MPayListener mPayListener) {
        this.mPayListener = mPayListener;
    }

    @Override
    public void setPayParam(MPayModel mPayModel) {
        this.mPayModel = mPayModel;
    }

    @Override
    public void pay() {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {

                String payInfo = MAliPayInfoUtil.getPayInfo((MPayAliModel) mPayModel);
                PayTask aliPay = new PayTask((Activity) context);
                final String result = aliPay.pay(payInfo, true);

                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        PayResult payResult = new PayResult(result);
                        String resultInfo = payResult.getResult();// 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                        String resultStatus = payResult.getResultStatus();

                        Log.e(TAG, "a li pay error:");
                        Log.e(TAG, resultStatus + "==" + resultInfo);

                        if (TextUtils.equals(resultStatus, "9000")) { // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档

//                            Toast.makeText(context, "支付成功", Toast.LENGTH_SHORT).show();
                            if (mPayListener != null) {
                                mPayListener.onSuccess(resultInfo);
                            }

                        } else {
                            // 判断resultStatus 为非“9000”则代表可能支付失败
                            // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                            if (TextUtils.equals(resultStatus, "8000")) {
//                                Toast.makeText(context, "支付结果确认中", Toast.LENGTH_SHORT).show();
                                if (mPayListener != null) {
                                    mPayListener.onConfirm(resultInfo);
                                }

                            } else {
                                // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
//                                Toast.makeText(context, "支付失败", Toast.LENGTH_SHORT).show();
                                if (mPayListener != null) {
                                    mPayListener.onFailure(resultInfo);
                                }

                            }
                        }

                        if (mPayListener != null) {
                            mPayListener.onFinish(resultInfo);
                        }
                    }
                });
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

}
