package com.bashapplication.network.api;

import android.content.Context;

import com.bashapplication.network.https.HttpResultBean;
import com.bashapplication.network.https.SimpleCallBack;
import com.zhouyou.http.model.HttpParams;

import io.reactivex.disposables.Disposable;

public class LoginApi extends API {

    /**
     * 用户登录
     */

    public Disposable postUserLogin(Context context, HttpParams params,
                                    SimpleCallBack<HttpResultBean> callBack) {
        String url = "mbLogin/doLogin";
        return postParams(context, url, params, callBack);
    }
}
