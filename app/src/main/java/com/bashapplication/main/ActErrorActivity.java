package com.bashapplication.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ActErrorActivity  extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("");
        String error =  getIntent().getStringExtra("body");
        errorHint();
    }

    private void errorHint(){
        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(ActErrorActivity.this, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_LONG).show();
                Intent mIntent = new Intent(MyApplication.getInstance(), MainActivity.class);
                mIntent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getInstance().startActivity(mIntent);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                Looper.loop();
            }
        }.start();

    }
}
