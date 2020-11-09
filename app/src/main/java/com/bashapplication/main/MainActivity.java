package com.bashapplication.main;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.allenliu.versionchecklib.core.http.HttpParams;
import com.allenliu.versionchecklib.core.http.HttpRequestMethod;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.CustomDownloadingDialogListener;
import com.allenliu.versionchecklib.v2.callback.CustomVersionDialogListener;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.bashapplication.R;
import com.bashapplication.bash.BaseActivity;
import com.bashapplication.main.fragment.FollowFragment;
import com.bashapplication.main.fragment.HomePageFragment;
import com.bashapplication.main.fragment.MineFragment;
import com.bashapplication.main.fragment.CategoryFragment;
import com.bashapplication.main.fragment.PurchaseOrderFragment;
import com.bashapplication.network.https.HttpUtils;
import com.bashapplication.view.BaseDialog;
import com.blankj.utilcode.util.ToastUtils;
import com.tbruyelle.rxpermissions3.RxPermissions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {


    private String urlApk = Environment.getExternalStorageDirectory() + "/zsyjxs/app/";
    private static final int OUT_TIME = 2000;

    @BindView(R.id.tb)
    LinearLayout tb;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    /**
     * 主页
     */
    private HomePageFragment homePageFragment;


    /**
     * 品类
     */
    private CategoryFragment categoryFragment;


    /**
     * 关注
     */
    private FollowFragment followFragment;


    /**
     * 进货单
     */
    private PurchaseOrderFragment purchaseOrderFragment;

    /**
     * 我的
     */
    private MineFragment mineFragment;

    private FragmentManager fm;

    private DownloadBuilder builder;

    public int inFlag;


    @Override
    protected int layoutViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        fm = getSupportFragmentManager();
        bottomNavigationBar.setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setActiveColor(R.color.colorff5034)
                .setInActiveColor(R.color.color999999)
                .setBackgroundResource(R.color.white);

        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.tab_home_select,"首页").setInactiveIconResource(R.mipmap.tab_home_normal))
                .addItem(new BottomNavigationItem(R.mipmap.tab_class_select,"品类").setInactiveIconResource(R.mipmap.tab_class_normal))
                .addItem(new BottomNavigationItem(R.mipmap.tab_follow_select,"关注").setInactiveIconResource(R.mipmap.tab_follow_normal))
                .addItem(new BottomNavigationItem(R.mipmap.tab_purchaseorder_select,"进货单").setInactiveIconResource(R.mipmap.tab_purchaseorder_normal))
                .addItem(new BottomNavigationItem(R.mipmap.tab_mine_select,"我的").setInactiveIconResource(R.mipmap.tab_mine_normal))
                .initialise();



        setDefaultFragment();
        // getUpgrade();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setPermissions();

    }

    private void setPermissions() {
        RxPermissions rxPermissions = new RxPermissions(activity);
        String permissions[] = {
//                Manifest.permission.INTERNET,
//                Manifest.permission.ACCESS_NETWORK_STATE,
//                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.READ_PHONE_STATE,
//                Manifest.permission.ACCESS_WIFI_STATE,
//                Manifest.permission.CHANGE_WIFI_STATE,
//                Manifest.permission.REQUEST_INSTALL_PACKAGES
        };
        rxPermissions
                .request(permissions)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        Toast.makeText(activity, "yes", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(activity, "no", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = fm.beginTransaction();
        homePageFragment = HomePageFragment.newInstance("首页");
        transaction.replace(R.id.tb, homePageFragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (homePageFragment == null) {
                    homePageFragment = homePageFragment.newInstance("首页");
                }
                transaction.replace(R.id.tb, homePageFragment);
                break;
            case 1:
                if (categoryFragment == null) {
                    categoryFragment = CategoryFragment.newInstance("品类");
                }
                transaction.replace(R.id.tb, categoryFragment);
                break;
            case 2:
                if (followFragment == null) {
                    followFragment = FollowFragment.newInstance("关注");
                }
                transaction.replace(R.id.tb, followFragment);
                break;
            case 3:
                if (purchaseOrderFragment == null) {
                    purchaseOrderFragment = PurchaseOrderFragment.newInstance("进货单");
                }
                transaction.replace(R.id.tb, purchaseOrderFragment);
                break;
            case 4:
                if (mineFragment == null) {
                    mineFragment = MineFragment.newInstance("我的");
                }
                transaction.replace(R.id.tb, mineFragment);
                break;


        }

        transaction.commitAllowingStateLoss();// 事务提交
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    /**
     * 双击退出计时
     */
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if ((System.currentTimeMillis() - exitTime) > OUT_TIME) {
                ToastUtils.showShort("再次点击将退出");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    /**
     * 版本检测升级
     */
    String titles;
    String remarks;
    String urls;

    private void getUpgrade() {
        String versionName;
        int versionCode = 0;
        try {
            versionName = activity.getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            versionCode = activity.getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        File file = new File(urlApk);
        if (file.isFile()) {
            file.delete();
        }

        HttpParams httpParams = new HttpParams();
        httpParams.put("app", 2);
        httpParams.put("type", 1);
        httpParams.put("version", versionCode);
        builder = AllenVersionChecker
                .getInstance()
                .requestVersion()
                .setRequestMethod(HttpRequestMethod.POST)
                .setRequestParams(httpParams)
                .setRequestUrl(HttpUtils.getInstance().BASE_URL + "getappversion")
                .request(new RequestVersionListener() {
                    @Nullable
                    @Override
                    public UIData onRequestVersionSuccess(String result) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            JSONObject jsonObject12 = jsonObject.getJSONObject("data");
                            boolean irRun = jsonObject12.getBoolean("isNew");
                            if (!irRun) {
                                titles = jsonObject12.getString("title");
                                remarks = jsonObject12.getString("remark");
                                urls = jsonObject12.getString("url");

                                String isUpdate = jsonObject12.getString("needUpdate");

                                if ("1".equals(isUpdate)) {
                                    //强制更新
                                    builder.setForceUpdateListener(() -> {
                                        forceUpdate();
                                    });
                                }

                            } else {
                                return null;
                            }
                        } catch (JSONException e) {
                            return null;
                        }


                        return crateUIData(titles, remarks, urls);
                    }

                    @Override
                    public void onRequestVersionFailure(String message) {
                        Toast.makeText(MainActivity.this, "request failed", Toast.LENGTH_SHORT).show();

                    }
                });
        //强制更新
//        builder.setForceUpdateListener(() -> {
//            forceUpdate();
//        });

        //显示下载通知栏
        builder.setShowNotification(true);
        builder.setForceRedownload(true);
        // 静默下载+直接安装
        builder.setDirectDownload(false);
        builder.setShowDownloadingDialog(true);
        //显示下载失败对话框
        builder.setShowDownloadFailDialog(false);


        //自定义下载路径
        builder.setDownloadAPKPath(urlApk);

        builder.setCustomVersionDialogListener(createCustomDialogTwo());
        builder.setCustomDownloadingDialogListener(createCustomDownloadingDialog());
        builder.setOnCancelListener(() -> {
            Toast.makeText(MainActivity.this, "取消升级", Toast.LENGTH_SHORT).show();
        });
        builder.executeMission(this);
    }

    /**
     * 强制更新操作
     * 通常关闭整个activity所有界面，这里方便测试直接关闭当前activity
     */
    private void forceUpdate() {
        finish();
    }

    private UIData crateUIData(String title, String contents, String urls) {

        UIData uiData = UIData.create();
        uiData.setTitle(title);
        uiData.setDownloadUrl(urls);
        uiData.setContent(contents);
        return uiData;
    }

    private CustomVersionDialogListener createCustomDialogTwo() {
        return (context, versionBundle) -> {
            BaseDialog baseDialog = new BaseDialog(context, R.style.BaseDialog, R.layout.custom_dialog_two_layout);
            TextView textView = baseDialog.findViewById(R.id.tv_msg);
            TextView textView2 = baseDialog.findViewById(R.id.tv_title);
            textView.setText(versionBundle.getContent());
            textView2.setText(versionBundle.getTitle());
            baseDialog.setCanceledOnTouchOutside(true);
            return baseDialog;
        };
    }

    /**
     * 自定义下载中对话框，下载中会连续回调此方法 updateUI
     * 务必用库传回来的context 实例化你的dialog
     *
     * @return
     */
    private CustomDownloadingDialogListener createCustomDownloadingDialog() {
        return new CustomDownloadingDialogListener() {
            @Override
            public Dialog getCustomDownloadingDialog(Context context, int progress, UIData versionBundle) {
                BaseDialog baseDialog = new BaseDialog(context, R.style.BaseDialog, R.layout.custom_download_layout);
                return baseDialog;
            }

            @Override
            public void updateUI(Dialog dialog, int progress, UIData versionBundle) {
                TextView tvProgress = dialog.findViewById(R.id.tv_progress);
                ProgressBar progressBar = dialog.findViewById(R.id.pb);
                progressBar.setProgress(progress);
                tvProgress.setText(getString(R.string.versionchecklib_progress, progress));
            }
        };
    }


}