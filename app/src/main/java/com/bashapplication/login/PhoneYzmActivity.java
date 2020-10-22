package com.bashapplication.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bashapplication.R;
import com.bashapplication.bash.BaseActivity;
import com.jyn.vcview.VerificationCodeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 输入验证码登录
 */
public class PhoneYzmActivity extends BaseActivity {


    @BindView(R.id.tv_post_code)
    TextView tvPostCode;
    @BindView(R.id.tv_yzm)
    VerificationCodeView tvYzm;
    private static final int ONE_MINUTE = 60 * 1000;
    private  CountDownTimer timer;
    private String textYzm;
    @Override
    protected int layoutViewId() {
        return R.layout.activity_phone_yzm;

    }

    @Override
    protected void initViews() {
        initTitleBar(R.string.phone_login);
        tvYzm.setmEtWidth(60);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        tvYzm.setOnCodeFinishListener(new VerificationCodeView.OnCodeFinishListener() {
            @Override
            public void onTextChange(View view, String content) {

            }
            @Override
            public void onComplete(View view, String content) {
                Toast.makeText(activity, "aaaaaa="+content, Toast.LENGTH_SHORT).show();
                textYzm = content;
            }
        });
    }


    @OnClick({R.id.tv_sure, R.id.ll_bottom,R.id.tv_post_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sure:


                if(TextUtils.isEmpty(textYzm)){
                    toastShort("验证码不能为空");
                }
                if(textYzm.length()<6){
                    toastShort("验证码错误");
                }
                break;
            case R.id.ll_bottom:
                //h5
                break;
            case R.id.tv_post_code:

                //验证码
                resetDownTimer(ONE_MINUTE);
                break;
        }
    }

    public void resetDownTimer(long minute){
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        timer = new CountDownTimer(minute, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvPostCode.setTextColor(getResources().getColorStateList(R.color.colorc1c1cd));
                tvPostCode.setText("获取验证码(" + millisUntilFinished / 1000 + "秒)");
            }

            @Override
            public void onFinish() {
                tvPostCode.setTextColor(getResources().getColorStateList(R.color.color4348c1));
                tvPostCode.setText(R.string.register_post_code);
                tvPostCode.setEnabled(true);
            }
        }.start();
    }
}