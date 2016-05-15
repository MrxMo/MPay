package com.mrmo.mpaylib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.mrmo.mpaylib.observer.MObserverAble;
import com.mrmo.mpaylib.observer.MWeChatObserverAble;
import com.mrmo.mpaylib.util.MWeChatListenerUtil;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * 微信支付结果回调
 * Created by moguangjian on 16/1/28 11:39.
 */
public abstract class MWeChatPayResultActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = MWeChatPayResultActivity.class.getSimpleName();

    private IWXAPI api;

    protected abstract void onPaySuccess(String info);

    protected abstract void onPayFailure(String info);

    protected abstract void onPayCancel(String info);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, MPayConfig.WE_CHAT_PAY_APP_ID);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.e(TAG, "WeChat pay status code = " + baseResp.errCode);
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (baseResp.errCode == 0) {//成功
                MWeChatListenerUtil.instance().notifyPaySuccess(baseResp.errStr);
                onPaySuccess(baseResp.errStr);

            } else if (baseResp.errCode == -1) {//错误。可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
                MWeChatListenerUtil.instance().notifyPayFailure(baseResp.errStr);
                onPayFailure(baseResp.errStr);

            } else if (baseResp.errCode == -2) {//用户取消	无需处理。发生场景：用户不支付了，点击取消，返回APP。
                MWeChatListenerUtil.instance().notifyPayCancel(baseResp.errStr);
                onPayCancel(baseResp.errStr);

            } else {
                MWeChatListenerUtil.instance().notifyPayFailure(baseResp.errStr);
                onPayFailure(baseResp.errStr);
            }
        }

        finish();
    }

}