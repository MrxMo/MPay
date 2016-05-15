package com.mrmo.mpay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mrmo.mpaylib.MPayAli;
import com.mrmo.mpaylib.MPayBridge;
import com.mrmo.mpaylib.MPayConfig;
import com.mrmo.mpaylib.MPayListener;
import com.mrmo.mpaylib.MPayWeChat;
import com.mrmo.mpaylib.model.MPayAliModel;
import com.mrmo.mpaylib.model.MPayWeChatModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvAliPay;
    private TextView tvWeChatPay;

    private MPayBridge mPayBridge;

    private void assignViews() {
        tvAliPay = (TextView) findViewById(R.id.tvAliPay);
        tvWeChatPay = (TextView) findViewById(R.id.tvWeChatPay);

        tvAliPay.setOnClickListener(this);
        tvWeChatPay.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignViews();

        initMPay();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPayBridge.removeOnPayListener();
    }

    private void initMPay() {
        //只在支付宝支付起作用：设置是本地签名还是服务器签名
        MPayConfig.A_LI_PAY_SIGN_FROM_SERVICE = false;

        mPayBridge = new MPayBridge(this);
        mPayBridge.setOnPayListener(new MPayListener() {
            @Override
            public void onSuccess(String resultInfo) {
                Toast.makeText(getApplicationContext(), "支付成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onConfirm(String resultInfo) {
                Toast.makeText(getApplicationContext(), "支付结果确认中", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String resultInfo) {
                Toast.makeText(getApplicationContext(), "支付失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(String resultInfo) {
                Toast.makeText(getApplicationContext(), "支付取消", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish(String resultInfo) {
                // 最后回调的方法(无论什么状态，最后一定会回调该方法)
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvAliPay:
                mPayBridge.setmPayAble(new MPayAli(this));

                //手动签名
                MPayAliModel mPayAliModel = new MPayAliModel();
                mPayAliModel.setOrderId("商户网站唯一订单号");
                mPayAliModel.setSubject("商品名称");
                mPayAliModel.setBody("商品详情");
                mPayAliModel.setPrice("商品金额 >= 0.01");
                mPayAliModel.setNotifyUrl("服务器异步通知页面路径");

                mPayAliModel.setPartnerId("签约合作者身份ID");
                mPayAliModel.setSeller("签约卖家支付宝账号");
                mPayAliModel.setRsaPrivateKey("商户私钥，pkcs8格式");

//                mPayAliModel.setPayInfo("服务器直接返回签名的信息");
                mPayBridge.setPayParam(mPayAliModel);
                mPayBridge.pay();
                break;

            case R.id.tvWeChatPay:
                mPayBridge.setmPayAble(new MPayWeChat(this));
                MPayWeChatModel mPayWeChatModel = new MPayWeChatModel();
                mPayWeChatModel.setAppId("wxd930ea5d5a258f4f");
                mPayWeChatModel.setPartnerId("1900000109");
                mPayWeChatModel.setPrepayId("1101000000140415649af9fc314aa427");
                mPayWeChatModel.setNonceStr("1101000000140429eb40476f8896f4c9");
                mPayWeChatModel.setPackageValue("Sign=WXPay");
                mPayWeChatModel.setTimeStamp("1398746574" + "");
                mPayWeChatModel.setSign("7FFECB600D7157C5AA49810D2D8F28BC2811827B");
                mPayBridge.setPayParam(mPayWeChatModel);
                mPayBridge.pay();
                break;
        }
    }
}
