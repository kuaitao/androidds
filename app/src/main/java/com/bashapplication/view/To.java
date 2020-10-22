package com.bashapplication.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bashapplication.R;
import com.bashapplication.main.MyApplication;


public class To {

    private static final boolean DEBUG = true;
    private static Toast toast;

    public static void l(Context context, String text) {
        if (DEBUG)
            Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void l(Context context, Throwable tr) {
        String text = tr.getMessage();
        l(context, text != null ? text : "未知异常，请稍后重试！");
    }

    public static void s(Context context, String text) {
        if (DEBUG)
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void s(Context context, Throwable tr) {
        String text = tr.getMessage();
        s(context, text != null ? text : "未知异常，请稍后重试！");
    }

    public static void showCustomToast(String textStr) {
        View layout = LayoutInflater.from(MyApplication.getInstance()).inflate(R.layout.toast, null);
        TextView text = (TextView) layout.findViewById(R.id.toast_text_tv);
        text.setText(textStr);
        toast = new Toast(MyApplication.getInstance());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }


    public static void toastCancel() {
        if (toast != null) {
            toast.cancel();
        }
    }

    public static void showSuccessToast(String msg) {
        Toast toast = new Toast(MyApplication.getInstance());
        View view = View.inflate(MyApplication.getInstance(), R.layout.v_toast, null);
        ((TextView) view.findViewById(R.id.tv_content)).setText(msg);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(view);
        toast.show();
    }

    //    根据接口返回code，客户端Toast提示信息：
//            -1 ：服务器异常，请稍后再试
//-910 ：验证码获取频繁，请稍后再试
//-911：当前使用IP地址被禁止使用，请联系客服
//-912：手机号被禁止使用，请联系客服
//-999：无提示，直接返回登录页面
//-1000：服务器异常，请稍后再试
//-10000：服务器异常，请稍后再试
//
//
//-2....-100：业务逻辑校验错误，客户端逻辑处理
    public static void showServerErrorToast(int code) {
            switch (code) {
            case -1:
                showCustomToast("服务器异常，请稍后再试");
                return;
            case -910:
                showCustomToast("验证码获取频繁，请稍后再试");
                return;
            case -911:
                showCustomToast("当前使用IP地址被禁止使用，请联系客服");
                return;
            case -912:
                showCustomToast("手机号被禁止使用，请联系客服");
                return;
            case -1000:
                showCustomToast("服务器异常，请稍后再试");
                return;
            case -10000:
                showCustomToast("服务器异常，请稍后再试");
                return;
        }
    }
}
