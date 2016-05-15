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

//                Test data
//                String orderId = "14533442953080420";
//                String productName = "用户支付买手服务";
//                String productInfo = "买手服务费用";
//                String price = "0.01";
//                String notifyUrl = "http://pay.mmcmmc.net.cn/index.php";
//                String shopId = "2088121821333471";
//                String shopAccount = "zfmmcmmc@126.com";
//                String rsaKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALtkKaG8Hc+qYCKmoDqRsqWHNfEQySFRLTlmPUA5pi0SG8/sAvT9Ct8oSTn0xhTTvNdq8PCfJnKRm6/89VVDQpzIEx2rJqF8o0dHdBBxu+sdkbxcg5ME48oxi+1jmYCpEXoPPOnJ8wKZRepcTXiS01IR8RUJddXcRr/BRuHLmqVHAgMBAAECgYB+uZ3t4mnGplGdHVH+ScmmZ2nzPIbIIT2UM6/4XwQiNrzYP6NASl+vym664u2R6B8tDwUhtH8ru4yt10OSBYplkxK0jOwfv0ti4YgdPPzHz/4kmjOyaZvrw5Z+gyYtRkjJTYMsazeKh01HU3tagqWEJyTx8L0v4IdegzMwQD8KAQJBAO6s5aG/ncMbvA9dhUP41aNKDxarOQ6YcTTl9AE9Q77H0VuD1Cdl9T6DVY+Y32wZatEDvof9lmW/iNEiEDEnUJkCQQDI/kvRBebS+QWBxlTOmvJI5NrJfnKPoG8rlYLwqHsiKoQpBdO/6tLz2lc+jMcApkVymImUmv5p0zA5DWq4rPDfAkEAjIYrpXImNsvLi9hqMDz/u+ZMv/IL4/YJITYK1ff2PQt0zkbt0EyNekLlS2ickw7b/zgPJA5AUKPWsBs4IViecQJAMmtYKX8JgDLnpr5R1+IWlSKTn6gQpObh/FTtG+GnwdJYhNHpZGNLt9A1woMeCJOtKCoLmTOoTnOjzSwIcXswkQJAZP3ICj5PgATo2wSMIgyQseJo2iyesFUnho56NBrxzP7lyPp8eYqFa4BpYsp5peM2PDWd7pHEhKsNm4LqqZNBWA==";
//

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
