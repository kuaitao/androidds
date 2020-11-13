package com.bashapplication.mine.map;

import android.content.Context;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.bashapplication.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.DecimalFormat;
import java.util.List;

public class Adapter_SearchAddress  extends BaseQuickAdapter<PoiInfo, BaseViewHolder> {

    private Context context;
    private LatLng currentLatLng;

    public Adapter_SearchAddress(List<PoiInfo> oList, Context context, LatLng currentLatLng) {
        super(R.layout.item_search_address, oList);
        this.context = context;
        this.currentLatLng = currentLatLng;

    }

    @Override
    protected void convert(BaseViewHolder helper, final PoiInfo item) {

        LatLng latLng = item.getLocation();
       // double distance= getDistance(currentLatLng, latLng);

        helper.setText(R.id.tv_name,item.name)
              .setText(R.id.tv_address,item.address)
              .setText(R.id.tv_distance,"");

        if (helper.getLayoutPosition() == 0) {
            helper.setImageResource(R.id.iv_point,R.drawable.point_main)
                    .setTextColor(R.id.tv_name,context.getResources().getColor(R.color.colorff5034));
        }else{
            helper.setImageResource(R.id.iv_point,R.drawable.point_gray)
                    .setTextColor(R.id.tv_name,context.getResources().getColor(R.color.black));
        }

    }
    private String formatDistance(double distance){
        String str;
        if(distance>=1000){
            DecimalFormat df = new DecimalFormat("#.00");
            double b = distance/1000;
            str=df.format(b)+"千米";
        }else{
            DecimalFormat df = new DecimalFormat("######0");
            str = df.format(distance)+"米";
        }
        return str;
    }
    public Double getDistance(LatLng start, LatLng end){
        double lat1 = (Math.PI/180)*start.latitude;
        double lat2 = (Math.PI/180)*end.latitude;

        double lon1 = (Math.PI/180)*start.longitude;
        double lon2 = (Math.PI/180)*end.longitude;

        //地球半径
        double R = 6378.137;

        //两点间距离 km，如果想要米的话，结果*1000
        double d =  Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1))*R;
        if(d<1)
            return (Double)(d*1000);
        else
            return Double.valueOf(String.format("%.2f",d*1000));
    }
}
