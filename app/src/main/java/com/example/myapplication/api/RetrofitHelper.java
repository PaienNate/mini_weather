package com.example.myapplication.api;

import com.jc.mvvmrxjavaretrofitsample.model.entity.CityList;
import com.jc.mvvmrxjavaretrofitsample.model.entity.Movie;
import com.jc.mvvmrxjavaretrofitsample.model.entity.Province;
import com.jc.mvvmrxjavaretrofitsample.model.entity.Response;

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
    private DouBanMovieService movieService;
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

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(DouBanMovieService.BASE_URL)
                .build();
        //创建这个接口对应的retrofit
        weatherRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(WeatherService.BASE_URL)
                .build();
        //这里创建了对应的retrofit数据，作为services
        movieService = retrofit.create(DouBanMovieService.class);
        // 新增对应的天气Service
        weatherService = weatherRetrofit.create(WeatherService.class);
    }
    //获取城市信息 - 底层方法
    public void getCityList(Observer<Province> observers)
    {//appid,secret
        weatherService.getWebProvinceList(app_id,app_secret)
                .map(new Function<CityList, List<Province>>() {
                    @Override
                    public List<Province> apply(CityList cityList) throws Exception {
                        return cityList.getProvinceList();
                    }
                }).flatMap(
                        new Function<List<Province>, ObservableSource<Province>>() {
                            @Override
                            public ObservableSource<Province> apply(List<Province> provinces) throws Exception {
                                return Observable.fromIterable(provinces);
                            }
                        }        )
                //指定处理的事件流在哪个线程中执行
                .subscribeOn(Schedulers.io())
                //指定最后的结果处于哪个线程中
                .observeOn(AndroidSchedulers.mainThread())
                //订阅者是传入的subscriber,在rxjava2里，光荣的变成了observer……
                .subscribe(observers);

    }
    //获取电影 - 最底层方法
    public void getMovies(Observer<Movie> observer, int start, int count) {
        //调用retrofit的API方法，获取到对应的返回值，由于Gson插件的存在，它会自动归类为对象
        movieService.getMovies(start, count)
                //Func1的< I,O >I,O模版分别为输入和输出值的类型，
                // 实现Func1的call方法对I类型进行处理后返回O类型数据，
                // 只是flatMap中执行的方法的返回类型为Observable类型
                .map(new Function<Response<List<Movie>>, List<Movie>>() {
                    //通过这个解除了外层的JSON壳，露出真正的本体
                    //麻了，rxjava和rxjava2居然还不一样！
                    @Override
                    public List<Movie> apply(Response<List<Movie>> listResponse) throws Exception {
                        return listResponse.getSubjects();
                    }
                })
                // 只是flatMap中执行的方法的返回类型为Observable类型
                .flatMap(new Function<List<Movie>, ObservableSource<Movie>>() {
                    //上方解码之后是Observable<List<Movie>>，将他转换为单个movie的观察者类型
                    @Override
                    public ObservableSource<Movie> apply(List<Movie> movies) {
                        return Observable.fromIterable(movies);
                    }
                })
                //指定处理的事件流在哪个线程中执行
                .subscribeOn(Schedulers.io())
                //指定最后的结果处于哪个线程中
                .observeOn(AndroidSchedulers.mainThread())
                //订阅者是传入的subscriber,在rxjava2里，光荣的变成了observer……
                .subscribe(observer);
    }
}
