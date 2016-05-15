package com.mrmo.mpaylib.observer;

/**
 * 微信支付通知
 * Created by moguangjian on 16/5/15 01:51.
 */
public class MWeChatSubject extends MSubject {


    public void notifyPaySuccess(String msg) {
        notifyPayStatus(0, msg);
    }
    public void notifyPayFailure(String msg) {
        notifyPayStatus(-1, msg);
    }
    public void notifyPayCancel(String msg) {
        notifyPayStatus(-2, msg);
    }

    private void notifyPayStatus(int payStatus, String msg) {
        for (MObserverAble mObserverAble : list) {
            if (mObserverAble instanceof MWeChatObserverAble) {
                MWeChatObserverAble mWeChatObserverAble = (MWeChatObserverAble) mObserverAble;
                if (payStatus == 0) {
                    mWeChatObserverAble.updatePaySuccess(msg);

                } else if(payStatus == -1) {
                    ((MWeChatObserverAble) mObserverAble).updatePayFailure(msg);

                }else if(payStatus == -2) {
                    ((MWeChatObserverAble) mObserverAble).updatePayCancel(msg);
                }

            }
        }
    }

}
