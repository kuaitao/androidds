package com.bashapplication.utils;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author zem
 * @date 2019/2/28.
 * description：
 */
public class Utils {
    //转换
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //刷新加在
    //如果有header 或者footer此方法不可用Called attach on a child which is not detached
    public static void rvNotifyItemRangeChanged(RecyclerView.Adapter adapter, List allDataList, List newAddList) {
        if (adapter == null || allDataList == null) {
            return;
        }
        int notifyItemCount = newAddList == null ? 0 : newAddList.size();
        int notifyItemPosition = allDataList == null ? 0 : allDataList.size() - notifyItemCount - 1;
        if (adapter != null) {
            try {
                if (allDataList.size() == notifyItemCount) {//加载第一页数据时
                    adapter.notifyDataSetChanged();
                } else {
                    adapter.notifyItemRangeChanged(notifyItemPosition < 0 ? 0 : notifyItemPosition, notifyItemCount);
                }
            } catch (Exception e) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    public static List<File> strToFile(List<String> stringList, List<String> stringList2, List<String> stringList3){
        List<File> fileList =new ArrayList<>();
        for (int i = 0; i <stringList.size() ; i++) {
            File file =new File(stringList.get(i));
            fileList.add(file);
        }
        for (int i = 0; i <stringList2.size() ; i++) {
            File file2 =new File(stringList2.get(i));
            fileList.add(file2);
        }
        for (int i = 0; i <stringList3.size() ; i++) {
            File file3 =new File(stringList3.get(i));
            fileList.add(file3);
        }
        return fileList;
    }


    /**
     * 获取过去第几天的日期
     *
     * @param past 天
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);

        return result;
    }
}
