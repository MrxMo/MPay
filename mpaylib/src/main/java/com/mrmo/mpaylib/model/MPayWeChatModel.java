package com.mrmo.mpaylib.model;

/**
 * Created by moguangjian on 16/1/21 13:44.
 */
public class MPayWeChatModel extends MPayModel {

    private String partnerId;           // 商户id
    private String appId;               // 商户在微信开放平台申请的应用id
    private String prepayId;            // 预支付订单id
    private String nonceStr;            // 随机串，防重发
    private String timeStamp;           // 时间戳，防重发
    private String packageValue;        // 商家根据文档填写的数据和签名
    private String sign;                // 商家根据微信开放平台文档对数据做的签名
    private String extData;             // 第三方app自定义字符串，微信不作解析，在回调时带回给第三方 注意：字符串长度不能超过1024


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getExtData() {
        return extData;
    }

    public void setExtData(String extData) {
        this.extData = extData;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
