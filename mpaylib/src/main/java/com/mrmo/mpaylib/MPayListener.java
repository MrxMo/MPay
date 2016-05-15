package com.mrmo.mpaylib;

/**
 * Created by moguangjian on 16/1/20 10:15.
 */
public interface MPayListener {
    void onSuccess(String resultInfo);

    void onConfirm(String resultInfo);

    void onFailure(String resultInfo);

    void onCancel(String resultInfo);

    void onFinish(String resultInfo);
}
