# MPay
安卓第三方支付库，默认整合了支付宝、微信支付功能。
<br/>

## 支持
Android 2.2+
<br/>

## 混淆
#### 支付宝支付
-libraryjars libs/alipaySdk-20160427.jar

-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}
<br/>

#### 微信支付
-libraryjars libs/libammsdk.jar
<br/>

## Gradle [下载aar](https://github.com/MrxMo/MPay/raw/master/release/mpaylib-v1.0.1-20170123.aar)
 ```
 compile 'com.github.MrxMo:MPay:v1.0.1'
 ```
 <br/>

## 使用
* 实例化MPayBridge

```		
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
```


* 支付宝 <br/>
MPayConfig.A_LI_PAY_SIGN_FROM_SERVICE = false;//只在支付宝支付起作用：设置是本地签名还是服务器签名

```
mPayBridge.setmPayAble(new MPayAli(this));
// 手动签名
MPayAliModel mPayAliModel = new MPayAliModel();
mPayAliModel.setOrderId("商户网站唯一订单号");
mPayAliModel.setSubject("商品名称");
mPayAliModel.setBody("商品详情");
mPayAliModel.setPrice("商品金额 >= 0.01");
mPayAliModel.setNotifyUrl("服务器异步通知页面路径");
mPayAliModel.setPartnerId("签约合作者身份ID");
mPayAliModel.setSeller("签约卖家支付宝账号");
mPayAliModel.setRsaPrivateKey("商户私钥，pkcs8格式");
// mPayAliModel.setPayInfo("服务器直接返回签名的信息");
mPayBridge.setPayParam(mPayAliModel);
mPayBridge.pay();                
```

* 微信<br/>
1) 先到[微信开发平台](https://open.weixin.qq.com "微信开发平台")注册相关信息，应用签名与包名必须注册[微信支付集成说明](https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=8_5 "微信支付集成说明")。<br/>
2) 在注册的包名下创建包“wxapi”，然后创建类“WXPayEntryActivity”，并继承“MWeChatPayResultActivity”<br/>
3) 在AndroidManifest.xml添加<br/>
```		
<activity
	android:name=".wxapi.WXPayEntryActivity"
   android:exported="true"
	android:launchMode="singleTop"/>
```

4) 在onDestroy方法中将监听器移除 <br/>
```
mPayBridge.removeOnPayListener();
```

```
mPayBridge.setmPayAble(new MPayWeChat(this));
MPayWeChatModel mPayWeChatModel = new MPayWeChatModel();
mPayWeChatModel.setAppId("appId");
mPayWeChatModel.setPartnerId("partnerId");
mPayWeChatModel.setPrepayId("prepayId");
mPayWeChatModel.setNonceStr("nonceStr");
mPayWeChatModel.setPackageValue("Sign=WXPay");
mPayWeChatModel.setTimeStamp("timeStamp");
mPayWeChatModel.setSign("sign");
mPayBridge.setPayParam(mPayWeChatModel);
mPayBridge.pay();                                
```
<br/>

## 作者
莫先生 Mr-Mo 



 