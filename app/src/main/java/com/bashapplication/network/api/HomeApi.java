package com.bashapplication.network.api;

import android.content.Context;

import com.bashapplication.network.https.HttpResultBean;
import com.bashapplication.network.https.SimpleCallBack;
import com.zhouyou.http.model.HttpParams;

import io.reactivex.disposables.Disposable;

public class HomeApi extends API {

    /**
     * 获取首页列表数据
     */
    public Disposable postHomeListData(Context context, HttpParams params,
                                    SimpleCallBack<HttpResultBean> callBack) {
        String url = "article";
        return get(url, params, callBack);
    }
}
