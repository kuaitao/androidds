package com.bashapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

/**
 * @author zem
 * @date 2019/4/9.
 * description：Android 跳转
 */
public class JumperUtils {

    public static void JumpTo(Context context, Class<?> cls) {
        try {
            Intent intent = new Intent(context, cls);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void JumpTo(Context context, Class<?> cls, int flag) {
        try {
            Intent intent = new Intent(context, cls);
            intent.setFlags(flag);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void JumpTo(Context context, Class<?> cls, Bundle bundle) {
        try {
            Intent intent = new Intent(context, cls);
            intent.putExtras(bundle);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void JumpTo(Context context, Class<?> cls, Bundle bundle, int flag) {
        try {
            Intent intent = new Intent(context, cls);
            intent.putExtras(bundle);
            intent.setFlags(flag);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void JumpToForResult(Activity activity, Class<?> cls, int requestCode) {
        try {
            Intent intent = new Intent(activity, cls);
            activity.startActivityForResult(intent, requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public static void JumpToForResult(Activity activity, Class<?> cls, int requestCode, Bundle bundle) {
        try {
            Intent intent = new Intent(activity, cls);
            intent.putExtras(bundle);
            activity.startActivityForResult(intent, requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void JumpToForResult(Fragment activity, Class<?> cls, int requestCode) {
        try {
            Intent intent = new Intent(activity.getActivity(), cls);
            activity.startActivityForResult(intent, requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void JumpToForResult(Fragment activity, Class<?> cls, int requestCode, Bundle bundle) {
        try {
            Intent intent = new Intent(activity.getActivity(), cls);
            intent.putExtras(bundle);
            activity.startActivityForResult(intent, requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void JumpTo(Context context, String url) {
        try {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
