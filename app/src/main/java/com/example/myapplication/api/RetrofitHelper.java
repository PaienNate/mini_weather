package com.example.myapplication.api;

import com.example.myapplication.bean.CityBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HaohaoChang on 2017/2/11.
 */
public class RetrofitHelper {
    private static final int DEFAULT_TIMEOUT = 10;
    private Retrofit retrofit;
    private Retrofit weatherRetrofit;
    public static String app_id = "wpqgjkqkqnmqt3gt";
    public static String app_secret = "Q1NqMnl6ZlZyRkNESUJDMWZ0ZjZJdz09";
    private WeatherService weatherService;
    OkHttpClient.Builder builder;

    /**
     * 获取RetrofitHelper对象的单例 - 测试 - develop支
     *
     * */
    private static class Singleton {
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }

    public static RetrofitHelper getInstance() {

        return Singleton.INSTANCE;

    }
    //初始化
    private RetrofitHelper() {
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //创建这个接口对应的retrofit
        weatherRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(WeatherService.BASE_URL)
                .build();
        //这里创建了对应的retrofit数据，作为services
        weatherService = weatherRetrofit.create(WeatherService.class);
    }
    //获取城市信息 - 底层方法
    public void getCityList(Observer<CityBean.Province> observers)
    {//appid,secret
        weatherService.getWebProvinceList(app_id,app_secret)
                .map(new Function<CityBean, List<CityBean.Province>>() {

                    @Override
                    public List<CityBean.Province> apply(CityBean cityBean) throws Exception {
                        return cityBean.getProvince();
                    }
                }).flatMap(
                        new Function<List<CityBean.Province>, ObservableSource<CityBean.Province>>() {
                            @Override
                            public ObservableSource<CityBean.Province> apply(List<CityBean.Province> provinces) throws Exception {
                                return Observable.fromIterable(provinces);
                            }
                        })
                //指定处理的事件流在哪个线程中执行
                .subscribeOn(Schedulers.io())
                //指定最后的结果处于哪个线程中
                .observeOn(AndroidSchedulers.mainThread())
                //订阅者是传入的subscriber,在rxjava2里，光荣的变成了observer……
                .subscribe(observers);
    }
}
