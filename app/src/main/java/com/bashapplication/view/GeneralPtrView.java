package com.bashapplication.view;

import android.content.Context;
import android.util.AttributeSet;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * 二次封装的SmartRefreshLayout
 */
public class GeneralPtrView extends SmartPtrView {
    public GeneralPtrView(Context context) {
        this(context, null);
    }

    public GeneralPtrView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public enum PTRTYPE {BOTH, REFRESH_ONLY, LOADMORE_ONEY, NONE}

    public interface OnPtrListener {
        void onPtrRefresh();

        void onPtrLoadMore();
    }

    public void setOnPtrListener(PTRTYPE ptrtype, final OnPtrListener listener) {
        if(ptrtype == PTRTYPE.NONE){
            setEnableRefresh(false);
            setEnableLoadMore(false);
        }else {
            setEnableRefresh(ptrtype == PTRTYPE.BOTH || ptrtype == PTRTYPE.REFRESH_ONLY);
            setEnableLoadMore(ptrtype == PTRTYPE.BOTH || ptrtype == PTRTYPE.LOADMORE_ONEY);

            if (ptrtype == PTRTYPE.BOTH || ptrtype == PTRTYPE.REFRESH_ONLY) {
                setOnRefreshListener(new OnRefreshListener() {
                    @Override
                    public void onRefresh(RefreshLayout refreshlayout) {
                        if (listener != null)
                            listener.onPtrRefresh();
                    }
                });
            }

            if (ptrtype == PTRTYPE.BOTH || ptrtype == PTRTYPE.LOADMORE_ONEY) {
                setOnLoadMoreListener(new OnLoadMoreListener() {
                    @Override
                    public void onLoadMore(RefreshLayout refreshlayout) {
                        if (listener != null)
                            listener.onPtrLoadMore();
                    }
                });
            }
        }
    }


    public void finishPtrRefresh() {
        finishRefresh();
    }

    public void finishPtrLoadMore() {
        finishLoadMore();
    }

    public void refreshComplete(){
        RefreshState state = getState();
        if(state == RefreshState.Refreshing){
            finishPtrRefresh();
        }else if(state == RefreshState.Loading){
            finishPtrLoadMore();
        }

    }

    public void refreshCompleteWithNoMoreData(){
        finishLoadMoreWithNoMoreData();
    }
}
