package com.bashapplication.view;

import android.content.Context;
import android.util.AttributeSet;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

public class SmartPtrView extends SmartRefreshLayout {
    public SmartPtrView(Context context) {
        this(context,null);
    }

    public SmartPtrView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        setRefreshHeader(new ClassicsHeader(getContext())
                //.setAccentColor(UIUtils.getColor(R.color.white))
                //.setPrimaryColor(UIUtils.getColor(R.color.main_color))
        );

        setHeaderMaxDragRate(1.3f);//下拉的最大高度为实际高度的1.3倍 默认2倍
        setHeaderTriggerRate(0.4f);//下拉到高度的一半就可以触发刷新
        setDisableContentWhenRefresh(true);//是否在刷新的时候禁止列表的操作
        setEnableFooterFollowWhenLoadFinished(true);//是否在全部加载结束之后Footer跟随内容(没有更多了的提示会不会自动缩回去)

        //ClassicsFooter.REFRESH_FOOTER_NOTHING = getContext().getString(R.string.nomoredata);//"没有更多数据了";

        setRefreshFooter(new ClassicsFooter(getContext())
                //.setSpinnerStyle(SpinnerStyle.Scale)
                //.setAnimatingColor(UIUtils.getColor(R.color.main_color))
        );

    }


}
