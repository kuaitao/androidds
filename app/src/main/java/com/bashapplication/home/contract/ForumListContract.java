package com.bashapplication.home.contract;

import android.content.Context;

import com.bashapplication.bash.IBaseView;
import com.bashapplication.login.bean.UserBean;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.model.HttpParams;


public interface ForumListContract {

    interface IForumListView extends IBaseView {
        void forumListSuccess(UserBean userBean);

        void forumListError(ApiException apiExc);
    }

    interface Presenter<T> {
        void onForumList(Context context, HttpParams httpParams);
    }
}
