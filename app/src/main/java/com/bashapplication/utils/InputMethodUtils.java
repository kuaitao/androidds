package com.bashapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.os.ResultReceiver;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Method;


/**
 * 键盘管理类
 * Created by zem
 * on 2018/12/6 .
 *
 * @author zem
 */

public class InputMethodUtils {
    private static Runnable mShowImeRunnable;

    /**
     * 设置一个view延迟显示或者隐藏输入法
     *
     * @param visible
     */
    public static void setViewInput(final View view, final boolean visible) {
        if (null == mShowImeRunnable) {
            mShowImeRunnable = () -> {
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                if (imm != null) {
                    showSoftInputUnchecked(view, imm, 0);
                }
            };
        }
        if (visible) {
            view.post(mShowImeRunnable);
        } else {
            view.removeCallbacks(mShowImeRunnable);
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    /**
     * 强制显示输入法
     *
     * @param view
     * @param imm
     * @param flags
     */
    private static void showSoftInputUnchecked(View view, InputMethodManager imm, int flags) {
        try {
            Method method = imm.getClass().getMethod("showSoftInputUnchecked", int.class, ResultReceiver.class);
            method.setAccessible(true);
            method.invoke(imm, flags, null);
        } catch (Exception e) {
            imm.showSoftInput(view, flags);
        }
    }

    /**
     * 隐藏输入法
     *
     * @param context
     */
    public static void hideSoftInput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 隐藏输入法
     */
    public static void hideSoftInput(Activity activity) {
        if (null != activity && activity.getCurrentFocus() != null) {
            ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * toggleSoftInput
     * 这个方法可以转换软件输入法在窗体中的显示状态。如果输入法当前是显示状态，那么该方法设置输入法隐藏。如果输入法当前是隐藏状态
     * ，则该方法设置输入法显示。
     *
     * @param context
     */
    public static void showSoftInput(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 判断输入法是否显示
     *
     * @param paramsContext
     * @return
     */
    public static boolean isInput(Context paramsContext) {
        InputMethodManager imm = (InputMethodManager) paramsContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.isActive();// 若返回true，则表示输入法打开
    }
}
