package com.bashapplication.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bashapplication.R;
import com.bashapplication.bash.BaseActivity;
import com.bashapplication.login.bean.UserBean;
import com.bashapplication.login.contract.LoginContract;
import com.bashapplication.login.country.Country;
import com.bashapplication.login.presenter.LoginPresenter;
import com.bashapplication.utils.JumperUtils;
import com.zhouyou.http.exception.ApiException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录
 */
public class PhoneLoginActivity extends BaseActivity implements LoginContract.ILoginView {


    @BindView(R.id.et_login_phone)
    EditText etLoginPhone;
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;
    @BindView(R.id.tv_country)
    TextView tvCountry;

    private static final int SECLECT_COUNTRY = 0x11;

    private LoginPresenter loginPresenter;

    @Override
    protected int layoutViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {

        initTitleBar(R.string.phone_login);

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


    @OnClick({R.id.tv_login, R.id.tv_country, R.id.ll_bottom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                String phone = etLoginPhone.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    toastShort("手机号码不能为空");
                    return;
                } else if (phone.length() < 11) {
                    toastShort("手机号码应该是11位数字");
                    return;
                }

                JumperUtils.JumpTo(activity, PhoneYzmActivity.class);
                break;
            case R.id.tv_country:
                //选择国家
                JumperUtils.JumpToForResult(activity, SelectCountryActivity.class, SECLECT_COUNTRY);
                break;
            case R.id.ll_bottom:
                //h5
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SECLECT_COUNTRY && resultCode == Activity.RESULT_OK) {
            Country country = Country.fromJson(data.getStringExtra("country"));
            tvCountry.setText("+" + country.code);
        }
    }

}