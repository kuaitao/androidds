package com.bashapplication.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.bashapplication.main.ActErrorActivity;
import com.bashapplication.main.MyApplication;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static CrashHandler INSTANCE = new CrashHandler();
    private static final String TAG="CrashHandler";
    private boolean debug=true;
    private static final String PATH= Environment.getExternalStorageDirectory().getPath()+"/shiyou/crashTest/log/";
    private static final String FILE_NAME="crash";
    private static final String FILE_NAME_SUFFIX=".txt";
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private CrashHandler() {
    }
    public static CrashHandler getInstance() {
        return INSTANCE;
    }
    public void init(Context ctx) {
        mContext = ctx.getApplicationContext();
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if(!handleException(ex) && mDefaultHandler!=null){
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread,ex);
        }else{
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                Log.d(TAG,"error:",e);
                e.printStackTrace();
            }
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

    }

    /**
     * 自定义错误处理，收集错误信息，发送错误报告
     * @param ex true:处理了该异常信息返回true,否则返回false;
     * @return
     */
    private boolean handleException(Throwable ex) {
        if(ex==null){
            return false;
        }
        errorHint();
        String error = saveCrashInfoToFile(ex);
        Intent mIntent = new Intent(MyApplication.getInstance(), ActErrorActivity.class);
        mIntent.putExtra("body",error);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getInstance().startActivity(mIntent);

        return true;
    }
    private void errorHint(){
        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_LONG).show();

                Looper.loop();
            }
        }.start();

    }
    private void dumpExceptionToSDCard(Throwable ex) {
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            if(debug){
                Log.w(TAG,"sdcard unmounted");
                return;
            }
        }
        File dir=new File(PATH);
        if(!dir.exists()){
            dir.mkdirs();
        }
        long current = System.currentTimeMillis();
        String time=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date(current));
        File file=new File(PATH,FILE_NAME+time+FILE_NAME_SUFFIX);

        try {
            PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.println(time);
            dumpPhoneInfo(pw);
            pw.println();
            ex.printStackTrace(pw);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 保存日志文件
     * @param ex
     * @return  返回文件名称,便于将文件传送到服务器
     */
    private String saveCrashInfoToFile(Throwable ex) {
        StringBuilder sb = new StringBuilder();
        sb.append("MODEL:" + android.os.Build.MODEL + "\n ");
        sb.append("RELEASE:" + android.os.Build.VERSION.RELEASE + "\n ");
        sb.append("SDK:" + android.os.Build.VERSION.SDK + "\n ");
        sb.append("BRAND:" + android.os.Build.BRAND + "\n");
        String info = null;
        ByteArrayOutputStream baos = null;
        PrintStream ps = null;
        try {
            baos = new ByteArrayOutputStream();
            ps = new PrintStream(baos);
            ex.printStackTrace(ps);
            byte[] date = baos.toByteArray();
            info = new String(date);
            date = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                if (baos != null) {
                    baos.close();
                    baos = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sb.append(info);


        return sb.toString();
    }

    private void dumpPhoneInfo(PrintWriter pw) throws PackageManager.NameNotFoundException {
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        pw.print("app Version:");
        pw.print(pi.versionName);
        pw.print("-");
        pw.println(pi.versionCode);
        //版本号
        pw.print("OS Version:");
        pw.print(Build.VERSION.RELEASE);
        pw.print("-");
        pw.println(Build.VERSION.SDK_INT);
        pw.print("Vendor:");
        pw.println(Build.MANUFACTURER);
        pw.print("model:");
        pw.println(Build.MODEL);
        pw.print("CPU ABI: ");
        pw.println(Build.CPU_ABI);

    }
}
