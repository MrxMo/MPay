package com.mrmo.mpaylib.util;

import com.mrmo.mpaylib.MPayConfig;
import com.mrmo.mpaylib.model.MPayAliModel;
import com.mrmo.mpaylib.sources.ali.SignUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by moguangjian on 16/1/21 14:35.
 */
public class MAliPayInfoUtil {

    public static String getPayInfo(MPayAliModel mPayAliModel) {
        String payInfo;

        if (MPayConfig.A_LI_PAY_SIGN_FROM_SERVICE) {
            payInfo = mPayAliModel.getPayInfo();

        } else {
            String orderInfo = getOrderInfo(
                    mPayAliModel.getOrderId(),
                    mPayAliModel.getSubject(),
                    mPayAliModel.getBody(),
                    mPayAliModel.getPrice(),
                    mPayAliModel.getNotifyUrl(),
                    mPayAliModel.getPartnerId(),
                    mPayAliModel.getSeller()
            );

            payInfo = signPayInfo(orderInfo, mPayAliModel.getRsaPrivateKey());
        }

        return payInfo;
    }

    /**
     * 获取签名方式
     */
    private static String getSignType() {
        return "sign_type=\"RSA\"";
    }

    private static String signPayInfo(String orderInfo, String rsaKey) {
        String sign = SignUtils.sign(orderInfo, rsaKey);
        try {
            rsaKey = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return orderInfo + "&sign=\"" + rsaKey + "\"&" + getSignType();
    }

    private static String getOrderInfo(String orderId, String subject, String body, String price, String notifyUrl, String partner, String seller) {
        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + partner + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + seller + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + orderId + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
//        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";
        orderInfo += "&notify_url=" + "\"" + notifyUrl + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

}
