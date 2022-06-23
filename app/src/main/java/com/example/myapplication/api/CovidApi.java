package com.example.myapplication.api;

import com.example.myapplication.bean.CovidBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CovidApi {
    String BASE_URL = "https://api.inews.qq.com/";
    //由于接口们分散很杂，所以直接了当一点
    /**
     * 获取省或者市的疫情情况的接口
     * @param adCode 省或者市的标码，注意省和市对应的对象内取数据函数不同
     * @param limit 获取条数，原装默认为30，不填写获取全部
     * @return 返回观察者用于Observer
     */
    @GET(BASE_URL + "newsqa/v1/query/pubished/daily/list")
    Observable<CovidBean> getWebProvinceList(@Query("adCode") String adCode,@Query("limit") int limit);




}
