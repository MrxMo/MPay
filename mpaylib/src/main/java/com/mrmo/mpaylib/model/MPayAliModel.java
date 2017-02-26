package com.mrmo.mpaylib.model;

/**
 * Created by moguangjian on 16/1/21 13:44.
 */
public class MPayAliModel extends MPayModel {

    /*****************************************************************************
     *                                                                           *
     * 注：服务器签名(默认)，只填写payInfo参数即可，本地签名请支付前传 “本地签名相关参数”  *
     *                                                                           *
     *****************************************************************************/

    /** 本地签名相关参数 **/

    private String partnerId;           // 签约合作者身份ID
    private String seller;              // 签约卖家支付宝账号
    private String rsaPrivateKey;       // 商户私钥，pkcs8格式
    private String rsaPublicKey;        // 商户公钥，pkcs8格式, 可选

    private String orderId;             // 商户网站唯一订单号
    private String subject;             // 商品名称
    private String body;                // 商品详情
    private String price;               // 商品金额 >= 0.01
    private String notifyUrl;           // 服务器异步通知页面路径

    /** 本地签名相关参数 end**/


    /** 服务器签名 **/

    private String payInfo;             // 服务签好名并拼接好的支付定单信息

    /** 服务器签名 end**/


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public void setRsaPrivateKey(String rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
    }

    public String getRsaPublicKey() {
        return rsaPublicKey;
    }

    public void setRsaPublicKey(String rsaPublicKey) {
        this.rsaPublicKey = rsaPublicKey;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
