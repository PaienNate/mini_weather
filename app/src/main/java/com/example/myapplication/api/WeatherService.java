package com.example.myapplication.api;

import com.example.myapplication.bean.CityBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface WeatherService {
    // https://r.inews.qq.com/api/ip2city?otype=jsonp
    String BASE_URL = "https://r.inews.qq.com/api/ip2city/";
    //根据IP反查城市，参数写死即可
    @GET("ip2city?otype=jsonp")
    Observable<CityBean> getWebProvinceList();
    //获取对应天气的接口


}
