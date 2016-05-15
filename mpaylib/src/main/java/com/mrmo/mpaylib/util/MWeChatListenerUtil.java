package com.mrmo.mpaylib.util;

import com.mrmo.mpaylib.observer.MWeChatSubject;

/**
 * Created by moguangjian on 16/5/15 02:00.
 */
public class MWeChatListenerUtil {
    private static MWeChatSubject mWeChatSubject;

    public static synchronized MWeChatSubject instance() {
        if (mWeChatSubject == null) {
            mWeChatSubject = new MWeChatSubject();
        }
        return mWeChatSubject;
    }
}
