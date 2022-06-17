package com.example.myapplication.api;

import com.pithyweather.app.model.CityList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HttpApi {
//获取全国省市县的接口?app_id=wpqgjkqkqnmqt3gt&app_secret=Q1NqMnl6ZlZyRkNESUJDMWZ0ZjZJdz09
@GET("address/list")
Call<CityBean> getWebProvinceList(String appid, String app_secret);
//TODO：获取全国疫情接口（测试实现）






}
