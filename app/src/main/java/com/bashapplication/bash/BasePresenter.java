package com.bashapplication.bash;

import java.lang.ref.WeakReference;


public class BasePresenter<V extends IBaseView> {
    private WeakReference<V> weakReference;
    protected V mView;

    public V getmView() {
        return mView;
    }

    public void attachView(V view) {
        this.weakReference = new WeakReference<>(view);
        mView = weakReference.get();
    }

    public void deatchView() {
        if (weakReference != null)
            weakReference.clear();
        weakReference = null;
        mView = null;
    }

    boolean isViewAttached() {
        return mView != null;
    }

}
