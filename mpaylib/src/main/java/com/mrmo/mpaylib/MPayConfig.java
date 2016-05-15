package com.mrmo.mpaylib;

import com.mrmo.mpaylib.util.MWeChatListenerUtil;

/**
 * Created by moguangjian on 16/1/21 14:51.
 */
public class MPayConfig {

    public static boolean A_LI_PAY_SIGN_FROM_SERVICE = true;//是否服务器签名拼接参数
    public static String WE_CHAT_PAY_APP_ID = "";//微信支付appId

    public static void init() {
        init(true);
    }

    public static void init(boolean isSignFromServer) {
        A_LI_PAY_SIGN_FROM_SERVICE = isSignFromServer;
        MWeChatListenerUtil.instance();
    }

}
