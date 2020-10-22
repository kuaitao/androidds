package com.bashapplication.login.contract;

import android.content.Context;

import com.bashapplication.bash.IBaseView;
import com.bashapplication.login.bean.UserBean;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.model.HttpParams;

public interface LoginContract {

    interface ILoginView extends IBaseView {
        void loginSuccess(UserBean userBean);

        void loginError(ApiException apiExc);
    }

    interface Presenter<T> {
        void onLogin(Context context,HttpParams httpParams);
    }
}
