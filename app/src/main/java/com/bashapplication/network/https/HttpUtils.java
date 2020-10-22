package com.bashapplication.network.https;


import com.bashapplication.network.api.LoginApi;

/**
 * Created by zem
 * on 2018/12/6 .
 *
 * @author zem
 */

public class HttpUtils {


    private volatile static HttpUtils singleton = null;

    public static HttpUtils getInstance() {
        if (singleton == null) {
            synchronized (HttpUtils.class) {
                if (singleton == null) {
                    singleton = new HttpUtils();
                }
            }
        }
        return singleton;
    }

    public LoginApi loginApi;//登录


    private HttpUtils() {
        loginApi = new LoginApi();

    }

    /**
     * 环境
     */

    public  final String BASE_URL ="precision/";


}
