package com.mrmo.mpaylib.observer;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moguangjian on 16/5/15 01:41.
 */
public abstract class MSubject {

    private static final String TAG = MSubject.class.getSimpleName();

    protected List<MObserverAble> list = new ArrayList<>();

    public void add(MObserverAble mObserver) {
        if (mObserver == null) {
            return;
        }

        boolean isAdd = list.add(mObserver);
        if (isAdd) {
            Log.i(TAG, "add observer . MSubject size:" + list.size());
        }
    }

    public void del(MObserverAble mObserver) {
        if (mObserver == null) {
            return;
        }

        boolean isRemove = list.remove(mObserver);
        if (isRemove) {
            Log.i(TAG, "remove observer . MSubject size:" + list.size());
        }
    }

    public void notifyM() {
        for (MObserverAble mObserver : list) {
            mObserver.update();
        }
    }
}
