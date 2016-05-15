package com.mrmo.mpaylib;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mrmo.mpaylib.model.MPayModel;
import com.mrmo.mpaylib.observer.MObserverAble;
import com.mrmo.mpaylib.observer.MWeChatObserverAble;
import com.mrmo.mpaylib.util.MWeChatListenerUtil;

/**
 * Created by moguangjian on 16/1/20 10:12.
 */
public class MPayBridge implements MPayAble {

    private static final String TAG = MPayBridge.class.getSimpleName();

    private Context context;
    private MPayAble mPayAble;
    private MPayModel mPayModel;
    private MPayListener mPayListener;

    public MPayBridge(Context context) {
        this.context = context;
        MWeChatListenerUtil.instance();
    }

    public MPayAble getmPayAble() {
        return mPayAble;
    }

    public void setmPayAble(MPayAble mPayAble) {
        removeOnPayListener();
        this.mPayAble = mPayAble;
    }

    @Override
    public void setOnPayListener(MPayListener mPayListener) {
        this.mPayListener = mPayListener;
    }

    @Override
    public void setPayParam(MPayModel mPayModel) {
        this.mPayModel = mPayModel;
    }

    @Override
    public void pay() {
        if (mPayModel == null) {
            Toast.makeText(context, "订单信息异常", Toast.LENGTH_SHORT).show();
            return;
        }
        mPayAble.setOnPayListener(mPayListener);
        addWeChatOnPayListener();
        mPayAble.setPayParam(mPayModel);
        mPayAble.pay();
    }

    private void addWeChatOnPayListener() {
        removeOnPayListener();
        if (getmPayAble() instanceof MWeChatObserverAble) {
            MWeChatListenerUtil.instance().add((MObserverAble) getmPayAble());
        }
    }

    public void removeOnPayListener() {
        if (getmPayAble() instanceof MWeChatObserverAble) {
            MWeChatListenerUtil.instance().del((MObserverAble) getmPayAble());
        }
    }
}
