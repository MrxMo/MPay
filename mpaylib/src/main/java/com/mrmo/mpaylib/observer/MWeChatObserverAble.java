package com.mrmo.mpaylib.observer;

/**
 * Created by moguangjian on 16/5/15 01:55.
 */
public interface MWeChatObserverAble extends MObserverAble{

    void updatePaySuccess(String msg);
    void updatePayFailure(String msg);
    void updatePayCancel(String msg);

}
