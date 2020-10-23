package com.bashapplication.view;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bashapplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class SexSelectDialog extends BottomSheetDialog implements View.OnClickListener {
    public SexSelectDialog(@NonNull Context context) {
        this(context,0);
    }

    public SexSelectDialog(@NonNull Context context, int theme) {
        this(context, true,null);
    }

    protected SexSelectDialog(@NonNull Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();


    }

    public void initView(){
        View view = View.inflate(getContext(), R.layout.dialog_bottom_six,null);
        setContentView(view);

        try {
            // hack bg color of the BottomSheetDialog
            ViewGroup parent = (ViewGroup) getWindow().getDecorView();
            parent.setBackgroundResource(android.R.color.transparent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        findViewById(R.id.tv_man).setOnClickListener(this);
        findViewById(R.id.tv_woman).setOnClickListener(this);
        findViewById(R.id.tv_secrecy).setOnClickListener(this);
        findViewById(R.id.tv_select_cancel).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_man){
            if(sexSelectListener != null){
                sexSelectListener.onSexResult(((TextView)v).getText().toString());
            }
        }else if(v.getId() == R.id.tv_woman){
            if(sexSelectListener != null){
                sexSelectListener.onSexResult(((TextView)v).getText().toString());
            }
        }
        else if(v.getId() == R.id.tv_secrecy){
            if(sexSelectListener != null){
                sexSelectListener.onSexResult(((TextView)v).getText().toString());
            }
        }else if(v.getId() == R.id.tv_select_cancel){

        }
        dismiss();
    }


    private OnSexSelectListener sexSelectListener;
    public void setSexSelectListener(OnSexSelectListener listener){
        this.sexSelectListener = listener;
    }

    public interface OnSexSelectListener{
        void onSexResult(String sex);
    }
}
