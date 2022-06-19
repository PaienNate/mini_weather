package com.example.myapplication.api;


import com.example.myapplication.bean.CityBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

//用于获取天气，信息等的接口总定义
public interface CityService {
    String BASE_URL = "https://www.mxnzp.com/api/";
    //获取全国省市县的接口
    //?app_id=wpqgjkqkqnmqt3gt&app_secret=Q1NqMnl6ZlZyRkNESUJDMWZ0ZjZJdz09
    @GET("address/list")
    Observable<CityBean> getWebProvinceList(@Query("app_id") String appid, @Query("app_secret") String app_secret);
    //获取对应天气的接口
}
