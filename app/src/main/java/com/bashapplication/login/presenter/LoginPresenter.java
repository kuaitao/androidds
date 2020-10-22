package com.bashapplication.login.presenter;

import android.content.Context;

import com.bashapplication.bash.BasePresenter;
import com.bashapplication.login.bean.UserBean;
import com.bashapplication.login.contract.LoginContract;
import com.bashapplication.network.https.HttpResultBean;
import com.bashapplication.network.https.HttpUtils;
import com.bashapplication.network.https.SimpleCallBack;
import com.bashapplication.utils.GsonUtil;
import com.blankj.utilcode.util.ObjectUtils;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.model.HttpParams;

public class LoginPresenter extends BasePresenter<LoginContract.ILoginView> implements LoginContract.Presenter {
    @Override
    public void onLogin(Context context,HttpParams httpParams) {

        HttpUtils.getInstance().loginApi.postUserLogin(context, httpParams, new SimpleCallBack<HttpResultBean>() {
            @Override
            public void onSuccess(HttpResultBean s) {

                if(!ObjectUtils.isEmpty(s.getData())){
                    UserBean userBean = GsonUtil.getRealBeanFromT(s.getData(), UserBean.class);

                    if (mView != null){
                        mView.loginSuccess(userBean);
                    }

                }
            }

            @Override
            public void onError(ApiException t) {

                if (mView != null) {
                    mView.loginError(t);
                    mView.hideLoading();
                }

            }
        });
    }


}
