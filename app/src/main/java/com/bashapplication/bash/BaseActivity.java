package com.bashapplication.bash;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.bashapplication.R;
import com.bashapplication.main.MyApplication;
import com.bashapplication.network.https.MyProgressDialog;
import com.bashapplication.utils.GlideUtils;
import com.bashapplication.utils.InputMethodUtils;
import com.bashapplication.utils.LeakFix;
import com.bashapplication.view.NoDoubleClickListener;
import com.bashapplication.view.To;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jessyan.autosize.internal.CustomAdapt;

public abstract class BaseActivity<T extends BasePresenter>  extends AppCompatActivity implements IBaseView<T> , CustomAdapt {

    private Unbinder unbinder;

    protected LinearLayout back;

    protected FragmentActivity activity;

    public ImmersionBar mImmersionBar;

    private boolean statusColor = true;


    protected List<T> mPresenterList = new ArrayList<>();

   // protected LoadingDialog mLoading;

    protected BasePresenter createPresenter(Class presentClass) {
        try {
            Object object = presentClass.newInstance();
            T instancePresenter = (T) object;
            mPresenterList.add(instancePresenter);
            return instancePresenter;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//        getSupportActionBar().hide();
        activity = this;
        if (ActivityUtils.getTopActivity() != null) {
            LogUtils.i("入栈:" + ActivityUtils.getTopActivity().getClass().getSimpleName());
        }
        setContentView(layoutViewId());
        unbinder = ButterKnife.bind(this);

        setStatusColor();
        initViews();
        initData();
        setListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Configuration configuration = MyApplication.getInstance().getResources().getConfiguration();
        if (configuration.fontScale != 1.0f) {
            configuration.fontScale = 1.0f;//设置字体的缩放比例
            MyApplication.getInstance().getResources().updateConfiguration(configuration, MyApplication.getInstance().getResources().getDisplayMetrics());
        }
        GlideUtils.resumeRequests(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        GlideUtils.pauseRequests(this);
    }

    @Override
    protected void onDestroy() {
        if (null != unbinder) {
            unbinder.unbind();
        }

        LeakFix.fixInputMethodManagerLeak(this);
        // 必须调用该方法，防止内存泄漏
        if (null != mImmersionBar) {
            mImmersionBar.destroy();
        }

        for (T item : mPresenterList) {
            if (item != null)
                item.deatchView();
        }
//        if (mLoading != null)
//            mLoading.dismiss();
//
        super.onDestroy();
        if (!ActivityUtils.isActivityExistsInStack(this)) {
            LogUtils.i("出栈:" + this.getClass().getSimpleName());
        }
    }

    public BaseActivity() {
        super();
    }

    protected abstract int layoutViewId();

    protected abstract void initViews();

    protected abstract void initData();

    protected abstract void setListener();


    public void setStatusColor(boolean isSet) {
        this.statusColor = isSet;
    }

    private void setStatusColor() {
        mImmersionBar = ImmersionBar.with(this);
        if (statusColor) {
            //white背景+深色文本颜色
            mImmersionBar.fitsSystemWindows(true).statusBarColor(statusBarColor()).keyboardEnable(true).statusBarDarkFont(true, 0.2f).init();
        } else {
            mImmersionBar.transparentStatusBar().init();
        }
    }

    protected int statusBarColor() {
        return R.color.white;
    }

    protected void initTitleBar(int titleResoureId) {
        initTitleBar(getString(titleResoureId), null);
    }

    protected void initTitleBar(int titleResoureId, int rightTextResourId) {
        initTitleBar(getString(titleResoureId), getString(rightTextResourId));
    }

    protected void initTitleBar(String titleText, String rightTextStr) {
        back = findViewById(R.id.title_back);
        if (back != null) {
            back.setOnClickListener(view -> onBackPressed());
        }

        TextView title = findViewById(R.id.title_title);
        if (title != null && !TextUtils.isEmpty(titleText)) {
            title.setText(titleText);
        }

        Button rightText = findViewById(R.id.title_right);
        if (rightText != null) {
            if (!TextUtils.isEmpty(rightTextStr)) {
                rightText.setText(rightTextStr);
                rightText.setVisibility(View.VISIBLE);
            } else {
                rightText.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 连续点击
     */
    public boolean isFastTwiceClick(View view) {
        return NoDoubleClickListener.isFastTwiceClick(view);
    }

    /**
     * 修改系统字体大小影响APP字体大小
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    /**
     * 适配
     */
    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 0;
    }

    @Override
    public void finish() {
        InputMethodUtils.hideSoftInput(this);
        super.finish();
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    /**
     * 弹出一个3s显示的toast框
     */
    public void toastShort(String msg) {
        To.showCustomToast(msg);
    }


}
