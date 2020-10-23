package com.bashapplication.home.presenter;
import android.content.Context;

import com.bashapplication.bash.BasePresenter;
import com.bashapplication.home.contract.ForumListContract;
import com.bashapplication.login.bean.UserBean;
import com.bashapplication.network.https.HttpResultBean;
import com.bashapplication.network.https.HttpUtils;
import com.bashapplication.network.https.SimpleCallBack;
import com.bashapplication.utils.GsonUtil;
import com.blankj.utilcode.util.ObjectUtils;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.model.HttpParams;

public class ForumListPresenter extends BasePresenter<ForumListContract.IForumListView> implements ForumListContract.Presenter {


    @Override
    public void onForumList(Context context, HttpParams httpParams) {

        HttpUtils.getInstance().homeApi.postHomeListData(context, httpParams, new SimpleCallBack<HttpResultBean>() {
            @Override
            public void onSuccess(HttpResultBean s) {

                if(!ObjectUtils.isEmpty(s.getData())){
                    UserBean userBean = GsonUtil.getRealBeanFromT(s.getData(), UserBean.class);

                    if (mView != null){
                        mView.forumListSuccess(userBean);
                    }

                }
            }

            @Override
            public void onError(ApiException t) {

                if (mView != null) {
                    mView.forumListError(t);
                    mView.hideLoading();
                }

            }
        });

    }
}