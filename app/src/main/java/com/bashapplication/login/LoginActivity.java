package com.bashapplication.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bashapplication.R;
import com.bashapplication.bash.BaseActivity;
import com.bashapplication.login.bean.UserBean;
import com.bashapplication.login.contract.LoginContract;
import com.bashapplication.login.presenter.LoginPresenter;
import com.zhouyou.http.exception.ApiException;

public class LoginActivity extends BaseActivity implements LoginContract.ILoginView{


    private LoginPresenter loginPresenter;

    @Override
    protected int layoutViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {

        loginPresenter = (LoginPresenter) createPresenter(LoginPresenter.class);
        loginPresenter.attachView(this);


    }

    @Override
    protected void initData() {

       // loginPresenter.onLogin(activity,);

    }

    @Override
    protected void setListener() {

    }


    @Override
    public void loginSuccess(UserBean userBean) {

    }

    @Override
    public void loginError(ApiException apiExc) {

    }
}