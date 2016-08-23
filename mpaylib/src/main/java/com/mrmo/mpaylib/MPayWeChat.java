package com.mrmo.mpaylib;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mrmo.mpaylib.model.MPayModel;
import com.mrmo.mpaylib.model.MPayWeChatModel;
import com.mrmo.mpaylib.observer.MWeChatObserverAble;
import com.mrmo.mpaylib.util.MWeChatListenerUtil;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * 微信支付
 * Created by moguangjian on 16/1/21 11:46.
 */
public class MPayWeChat implements MPayAble, MWeChatObserverAble {

    private final String TAG = MPayWeChat.class.getSimpleName();

    private Context context;
    private IWXAPI iwxapi;
    private MPayModel mPayModel;
    private MPayListener mPayListener;

    public MPayWeChat(Context context) {
        this.context = context;
    }

    private synchronized IWXAPI getIwxapiInstance() {
        if (iwxapi == null && mPayModel != null) {
            MPayWeChatModel mPayWeChatModel = (MPayWeChatModel) mPayModel;
            if (mPayWeChatModel != null
                    && mPayWeChatModel.getAppId() != null
                    && !mPayWeChatModel.getAppId().equals("")
                    ) {
                iwxapi = WXAPIFactory.createWXAPI(context, mPayWeChatModel.getAppId());
                MPayConfig.WE_CHAT_PAY_APP_ID = mPayWeChatModel.getAppId();
            }
        }
        if (iwxapi == null) {
            Log.e(TAG, "iwxapi is null ! iwxapi instance is failure !");
        }
        return iwxapi;
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
        if (mPayModel == null) {
            Toast.makeText(context, "订单信息异常", Toast.LENGTH_SHORT).show();
            MWeChatListenerUtil.instance().notifyPayFailure("订单信息异常");
            return;
        }

        if (getIwxapiInstance() == null) {
            Log.e(TAG, "iwxapi is null ! iwxapi instance is failure !");
            Toast.makeText(context, "支付失败", Toast.LENGTH_SHORT).show();
            MWeChatListenerUtil.instance().notifyPayFailure("支付失败");
            return;
        }

        if (!isInstalled()) {
            Toast.makeText(context, "未安装微信或微信版本过低", Toast.LENGTH_SHORT).show();
            MWeChatListenerUtil.instance().notifyPayFailure("未安装微信或微信版本过低");
            return;
        }

        if (!isPaySupported()) {
            Toast.makeText(context, "当前微信版本不支持支付功能", Toast.LENGTH_SHORT).show();
            MWeChatListenerUtil.instance().notifyPayFailure("当前微信版本不支持支付功能");
            return;
        }

        weChatPay((MPayWeChatModel) mPayModel);
    }

    private boolean isPaySupported() {
        boolean isPaySupported = false;
        if (getIwxapiInstance() != null) {
            isPaySupported = getIwxapiInstance().getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
        }
        return isPaySupported;
    }

    private boolean isInstalled() {
        boolean isInstalled = false;
        if (getIwxapiInstance() != null) {
            isInstalled = getIwxapiInstance().isWXAppInstalled();
        }
        return isInstalled;
    }

    private void weChatPay(MPayWeChatModel mPayWeChatModel) {
        try {
            PayReq req = new PayReq();
            req.appId = mPayWeChatModel.getAppId();
            req.partnerId = mPayWeChatModel.getPartnerId();
            req.prepayId = mPayWeChatModel.getPrepayId();
            req.nonceStr = mPayWeChatModel.getNonceStr();
            req.timeStamp = mPayWeChatModel.getTimeStamp();
            req.packageValue = mPayWeChatModel.getPackageValue();
            req.sign = mPayWeChatModel.getSign();
            req.extData = mPayWeChatModel.getExtData(); // optional

            getIwxapiInstance().registerApp(mPayWeChatModel.getAppId());// 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
            getIwxapiInstance().sendReq(req);

        } catch (Exception e) {
            Log.e(TAG, "error：" + e.getMessage());
            MWeChatListenerUtil.instance().notifyPayFailure("支付失败");
        }
    }

    @Override
    public void update() {
        Log.e(TAG, "update no implement");
    }

    @Override
    public void updatePaySuccess(String msg) {
        if (mPayListener != null) {
            mPayListener.onSuccess(msg);
            mPayListener.onFinish(msg);
        }
    }

    @Override
    public void updatePayFailure(String msg) {
        if (mPayListener != null) {
            mPayListener.onFailure(msg);
            mPayListener.onFinish(msg);
        }
    }

    @Override
    public void updatePayCancel(String msg) {
        if (mPayListener != null) {
            mPayListener.onCancel(msg);
            mPayListener.onFinish(msg);
        }
    }

}
