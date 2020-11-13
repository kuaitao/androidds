package com.bashapplication.main;

import androidx.multidex.MultiDexApplication;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.bashapplication.R;
import com.bashapplication.network.https.HttpUtils;
import com.bashapplication.view.CrashHandler;
import com.blankj.utilcode.util.Utils;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cookie.CookieManger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import me.jessyan.autosize.AutoSizeConfig;
import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author zem
 * @date 2019/2/25.
 * description：
 */
public class MyApplication extends MultiDexApplication {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化工具类
        Utils.init(this);
        instance = this;
        initHttp();
        initOkHttp();
      //  LeakCanary.install(this);
      //  CrashHandler.getInstance().init(this);
        LiveEventBus.config().setContext(this).lifecycleObserverAlwaysActive(true);
        AutoSizeConfig.getInstance().setCustomFragment(true);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);

    }

    //static 代码段可以防止内存泄露
    static {
        SmartRefreshLayout.setDefaultRefreshInitializer((context, layout) -> {
            layout.setEnableLoadMoreWhenContentNotFull(false);//是否在列表不满一页时候开启上拉加载功能
            layout.setDisableContentWhenRefresh(true);//是否在刷新的时候禁止列表的操作
            layout.setDisableContentWhenLoading(true);//是否在加载的时候禁止列表的操作
            layout.setEnableScrollContentWhenLoaded(true);//是否在加载完成时滚动列表显示新的内容
        });

        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            //全局设置主题颜色
            layout.setPrimaryColorsId(R.color.transparent);
            //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));
            // 指定为经典Header，默认是 贝塞尔雷达Header
            return new ClassicsHeader(context);
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }


    public static MyApplication getInstance() {
        return instance;
    }

    public void initHttp() {

        //设置请求头
        EasyHttp.init(instance);
        EasyHttp.getInstance().debug("bashapplication", true)
                .addConverterFactory(GsonConverterFactory.create())
                //如果不想让本库管理cookie,以下不需要
                .setCookieStore(new CookieManger(this)) //cookie持久化存储，如果cookie不过期，则一直有效
                .setReadTimeOut(30 * 1000)
                .setWriteTimeOut(30 * 1000)
                .setConnectTimeout(30 * 1000)
                .setRetryCount(0)
                .setCertificates()
                .setBaseUrl(HttpUtils.getInstance().BASE_URL);
    }

    private void initOkHttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new CookieManger(this))
                .addInterceptor(new LoggerInterceptor("bashapplication"))
                .retryOnConnectionFailure(true)
//                .addInterceptor(new LoggingInterceptor())
                .connectTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .writeTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
