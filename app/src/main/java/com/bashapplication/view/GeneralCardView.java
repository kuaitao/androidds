package com.bashapplication.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.cardview.widget.CardView;

import com.bashapplication.utils.DensityUtil;


public class GeneralCardView extends CardView {
    public GeneralCardView(Context context) {
        this(context, null);
    }

    public GeneralCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GeneralCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setRadius(DensityUtil.dip2px(14));
    }
}
