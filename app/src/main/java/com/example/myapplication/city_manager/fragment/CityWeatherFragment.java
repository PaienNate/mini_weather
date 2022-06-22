package com.example.myapplication.city_manager.fragment;


import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.WeatherBean;
import com.example.myapplication.db.DBManager;
import com.example.myapplication.service.GetWeatherService;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class CityWeatherFragment extends BaseFragment implements View.OnClickListener{
    TextView tempTv,cityTv,conditionTv,windTv,tempRangeTv,dateTv,clothIndexTv,carIndexTv,coldIndexTv,sportIndexTv,raysIndexTv,tipTv,umbrellaTv;
    //根据时间换图标
    ImageView dayIv;
    LinearLayout futureLayout;
    ScrollView outLayout;
    String url1 = "https://wis.qq.com/weather/common?source=pc&weather_type=observe|index|rise|alarm|air|tips|forecast_24h&province=";
    String url2 = "&city=";
    String city;
    String provice;

    private WeatherBean.DataBean.IndexBean index;
    private SharedPreferences pref;
    private int bgNum;

    //        换壁纸的函数
    public void exchangeBg(){
        pref = getActivity().getSharedPreferences("bg_pref", MODE_PRIVATE);
        bgNum = pref.getInt("bg", 2);
        switch (bgNum) {
            case 0:
                outLayout.setBackgroundResource(R.mipmap.bg);
                break;
            case 1:
                outLayout.setBackgroundResource(R.mipmap.bg2);
                break;
            case 2:
                outLayout.setBackgroundResource(R.mipmap.bg3);
                break;
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //插入这个city_weather的碎片
        View view = inflater.inflate(R.layout.fragment_city_weather, container, false);
        //初始化View
        initView(view);
        //根据设定调整北京图片
        exchangeBg();
//        可以通过activity传值获取到当前fragment加载的是那个地方的省份和天气情况
        Bundle bundle = getArguments();
        //获取activity的city值
        String provice_city = bundle.getString("city");
//        获取省份
        if(provice_city.split(" ").length>1)
        {   provice =provice_city.split(" ")[0];
            city = provice_city.split(" ")[1];
        }
        else
        {
            city = provice_city.split(" ")[0];
            provice = provice_city.split(" ")[0];
        }
        String url = url1+provice+url2+city;
//          调用父类获取数据的方法
        loadData(url);
        return view;
    }

    @Override
    public void onSuccess(String result) {
//        解析并展示数据
        try {
            parseShowData(result);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//         更新数据
        int i = DBManager.updateInfoByCity(city, result);
        if (i<=0) {
//            更新数据库失败，说明没有这条城市信息，增加这个城市记录
            DBManager.addCityInfo(city,result);
            //小组件：更新后，提示小组件跟着更新

        }
        GetWeatherService.notifyDatabaseUpdate();
    }
    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
//        数据库当中查找上一次信息显示在Fragment当中
        String s = DBManager.queryInfoByCity(city);
        if (!TextUtils.isEmpty(s)) {
            try {
                parseShowData(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }
    //判断是白天还是晚上
    public boolean getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String hour= sdf.format(new Date());
        int k  = Integer.parseInt(hour)  ;
        if ((k>=0 && k<6) ||(k >=18 && k<24)){
            return true;
        } else {
            return false;
        }
    }

    private Bitmap getImageFromAssetsFile(String fileName)
    {
        Bitmap image = null;
        AssetManager am = getResources().getAssets();
        try
        {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return image;

    }
    private void parseShowData(String result) throws ParseException {
//        使用gson解析数据
        WeatherBean weatherBean = new Gson().fromJson(result, WeatherBean.class);
        WeatherBean.DataBean resultsBean = weatherBean.getData();
        index = resultsBean.getIndex();


        //设置天气图标,首先获取时间，判断早晚
        WeatherBean.DataBean.ObserveBean observeBean = weatherBean.getData().getObserve();
        String weather_code = observeBean.getWeather_code();
        if (getCurrentTime()){
            //然后获取weatherBean里的天气代码，拼凑名称
            Toast.makeText(getActivity(),"晚上啦，早点休息哦~",Toast.LENGTH_SHORT).show();
            dayIv.setImageBitmap(getImageFromAssetsFile("night_" + weather_code + ".png"));
        }else {
            Toast.makeText(getActivity(), "现在是白天哦，心情愉快~", Toast.LENGTH_SHORT).show();
            dayIv.setImageBitmap(getImageFromAssetsFile("day_" + weather_code + ".png"));

        }
        //dayIv
//        设置TextView
        cityTv.setText(city);
        tipTv.setText(resultsBean.getTips().getObserve().get_$0());
//        获取今日天气情况
        WeatherBean.DataBean.ObserveBean todayDataBean = resultsBean.getObserve();
        String time = changeTime(todayDataBean.getUpdate_time());

        dateTv.setText("发布时间  "+time);

        windTv.setText("湿度 "+todayDataBean.getHumidity()+"%");
        tempRangeTv.setText("气压  "+todayDataBean.getPressure()+"hPa");
        conditionTv.setText(todayDataBean.getWeather_short());
//        获取实时天气温度情况，需要处理字符串
        tempTv.setText(todayDataBean.getDegree()+"°C");
//        获取未来三天的天气情况，加载到layout当中
        WeatherBean.DataBean.Forecast24hBean futureList = resultsBean.getForecast_24h();

        View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.item_main_center, null);
        itemView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        futureLayout.addView(itemView);
        TextView idateTv = itemView.findViewById(R.id.item_center_tv_date);
        TextView iconTv = itemView.findViewById(R.id.item_center_tv_con);
        TextView itemprangeTv = itemView.findViewById(R.id.item_center_tv_temp);
        TextView wind = itemView.findViewById(R.id.item_center_tv_winddirection);
//          获取对应的位置的天气情况
        idateTv.setText(futureList.get_$2().getTime()+"   明天");
        iconTv.setText(futureList.get_$2().getDay_weather());
        wind.setText(futureList.get_$2().getDay_wind_direction());
        itemprangeTv.setText(futureList.get_$2().getMin_degree()+"~"+futureList.get_$2().getMax_degree()+"°C");

        View itemView3 = LayoutInflater.from(getActivity()).inflate(R.layout.item_main_center, null);
        itemView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        futureLayout.addView(itemView3);
        TextView idateTv3 = itemView3.findViewById(R.id.item_center_tv_date);
        TextView iconTv3 = itemView3.findViewById(R.id.item_center_tv_con);
        TextView itemprangeTv3 = itemView3.findViewById(R.id.item_center_tv_temp);
        TextView wind3 = itemView3.findViewById(R.id.item_center_tv_winddirection);
//          获取对应的位置的天气情况
        idateTv3.setText(futureList.get_$3().getTime()+"   后天");
        iconTv3.setText(futureList.get_$3().getDay_weather());
        wind3.setText(futureList.get_$3().getDay_wind_direction());
        itemprangeTv3.setText(futureList.get_$3().getMin_degree()+"~"+futureList.get_$3().getMax_degree()+"°C");

        View itemView2 = LayoutInflater.from(getActivity()).inflate(R.layout.item_main_center, null);
        itemView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        futureLayout.addView(itemView2);
        TextView idateTv2 = itemView2.findViewById(R.id.item_center_tv_date);
        TextView iconTv2 = itemView2.findViewById(R.id.item_center_tv_con);
        TextView itemprangeTv2 = itemView2.findViewById(R.id.item_center_tv_temp);
        TextView wind2 = itemView2.findViewById(R.id.item_center_tv_winddirection);
//          获取对应的位置的天气情况
        idateTv2.setText(futureList.get_$4().getTime()+" 大后天");
        iconTv2.setText(futureList.get_$4().getDay_weather());
        wind2.setText(futureList.get_$4().getDay_wind_direction());
        itemprangeTv2.setText(futureList.get_$4().getMin_degree()+"~"+futureList.get_$4().getMax_degree()+"°C");

    }

//    时间格式化
    private String changeTime(String update_time) throws ParseException {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMddHHmm");
        SimpleDateFormat sf2 =new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String sfstr = "";
            sfstr = sf2.format(sf1.parse(update_time));
            return sfstr;
    }

    private void initView(View view) {
//        用于初始化控件操作
        tipTv  = view.findViewById(R.id.frag_tv_tips);

        tempTv = view.findViewById(R.id.frag_tv_currenttemp);
        cityTv = view.findViewById(R.id.frag_tv_city);
        conditionTv = view.findViewById(R.id.frag_tv_condition);
        windTv = view.findViewById(R.id.frag_tv_wind);
        tempRangeTv = view.findViewById(R.id.frag_tv_temprange);
        dateTv = view.findViewById(R.id.frag_tv_date);
        clothIndexTv = view.findViewById(R.id.frag_index_tv_dress);
        carIndexTv = view.findViewById(R.id.frag_index_tv_washcar);
        coldIndexTv = view.findViewById(R.id.frag_index_tv_cold);
        sportIndexTv = view.findViewById(R.id.frag_index_tv_sport);
        raysIndexTv = view.findViewById(R.id.frag_index_tv_rays);

        dayIv = view.findViewById(R.id.frag_iv_today);
        futureLayout = view.findViewById(R.id.frag_center_layout);

        outLayout = view.findViewById(R.id.out_layout);
        umbrellaTv = view.findViewById(R.id.frag_index_tv_umbrella);
//        设置点击事件的监听
        clothIndexTv.setOnClickListener(this);
        carIndexTv.setOnClickListener(this);
        coldIndexTv.setOnClickListener(this);
        sportIndexTv.setOnClickListener(this);
        raysIndexTv.setOnClickListener(this);
        umbrellaTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        switch (v.getId()) {
            case R.id.frag_index_tv_dress:
                builder.setTitle("穿衣指数");
                WeatherBean.DataBean.IndexBean.ClothesBean cloth = index.getClothes();
                String msg = cloth.getInfo()+"\n"+cloth.getDetail();
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
            case R.id.frag_index_tv_washcar:
                builder.setTitle("洗车指数");
                WeatherBean.DataBean.IndexBean.CarwashBean car = index.getCarwash();
                msg = car.getInfo()+"\n"+car.getDetail();
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
            case R.id.frag_index_tv_cold:
                builder.setTitle("感冒指数");
                WeatherBean.DataBean.IndexBean.ColdBean cold = index.getCold();
                msg = cold.getInfo()+"\n"+cold.getDetail();
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
            case R.id.frag_index_tv_sport:
                builder.setTitle("运动指数");
                WeatherBean.DataBean.IndexBean.SportsBean sport = index.getSports();
                msg = sport.getInfo()+"\n"+sport.getDetail();
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
            case R.id.frag_index_tv_rays:
                builder.setTitle("紫外线指数");
                WeatherBean.DataBean.IndexBean.UltravioletBean ultraviolet = index.getUltraviolet();
                msg = ultraviolet.getInfo()+"\n"+ultraviolet.getDetail();
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
            case R.id.frag_index_tv_umbrella:
                builder.setTitle("雨伞指数");
                WeatherBean.DataBean.IndexBean.UmbrellaBean umbrella = index.getUmbrella();
                msg = umbrella.getInfo()+"\n"+umbrella.getDetail();
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
        }
        builder.create().show();
    }
}
