package com.example.myapplication.api;


import com.example.myapplication.bean.Ip2CityBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface WeatherApi {
    // https://r.inews.qq.com/api/ip2city?otype=jsonp
    String BASE_URL = "https://r.inews.qq.com/api/";
    //根据IP反查城市，参数写死即可
    //根据抓包盲猜了一手jsonp改成json就能去掉括号，果然不假。
    @GET("ip2city?otype=json")
    Observable<Ip2CityBean> getIpWithCity();
    //获取对应天气的接口


}
