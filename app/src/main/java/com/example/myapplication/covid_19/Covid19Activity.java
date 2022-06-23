package com.example.myapplication.covid_19;

import static com.example.myapplication.covid_19.covidutil.getTencentNewsProvinceFromCity;
import static com.example.myapplication.db.DBManager.FindProvince;
import static com.example.myapplication.db.DBManager.getProvinceCodeFromCityCode;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.api.RetrofitHelper;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.CovidBean;
import com.example.myapplication.bean.CovidNewsBean;
import com.example.myapplication.bean.Ip2CityBean;
import com.google.gson.Gson;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

//扩写实现BaseActivity
public class Covid19Activity extends BaseActivity{
    //默认按钮处省和市都传入进来
    private ProgressDialog progressDialog;
    TextView covid_tv_city;
    TextView bentuquezhen;
    TextView bentuwuzhengzhuang;
    TextView jingwaishuru;
    TextView siwangbingli;
    TextView gaofengxiandiqu;
    TextView zhongfengxiandiqu;
    TextView xianyouquezhen;
    TextView leijiquezhen;
    ListView subao;
    String city;
    String province;
    String province_code;
    Covid19NewsAdapter covid19NewsAdapter;
    List<CovidNewsBean.DataBean.ItemsBean> itemsBeanList;
    //由于接口不完全兼容，只能出此下策
    Ip2CityBean ip2CityBean;
    Observer<Ip2CityBean> ip2CityBeanObserver;
    Context mcontext = this;
    //Handler handler;
    String url1 = "https://api.inews.qq.com/newsqa/v1/query/inner/publish/modules/list?modules=localCityNCOVDataList,diseaseh5Shelf";
    String url2 = "https://api.dreamreader.qq.com/news/v1/province/news/list?province_code=";
    String url3 = "&page_size=10";
    //无需知道实现机制
    private void getCityFromIp()
    {
       ip2CityBeanObserver = new Observer<Ip2CityBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("IP反查","启动IP反查功能");
            }

            @Override
            public void onNext(Ip2CityBean cityBean) {
                Log.i("IP反查-疫情","获取到当前IP为" + cityBean.getCity());
                ip2CityBean = cityBean;

            }

            @Override
            public void onError(Throwable e) {
                Log.i("IP反查-疫情","出错，显示报错为");
                e.printStackTrace();
            }

            @Override
            //这是运行完的情况，可以继续其他操作，获取到一个对象ip2CityBean。
            public void onComplete() {
                Log.i("IP反查-疫情","IP反查成功，更新");

                //接口返回的数值需要使用对应的接口的省ID才能获取相应的省的信息

                //不用IP的可以通过存入的数据库直接获取到省ID
                //而IP的名字可能和数据库里的不完全一致，导致无法获取省ID，所以重新请求一次
                //比较是否为IP的

                //由于会传过来用IP的，首先先用这个请求，得到一个ip2CityBean
                //如果现在处理的城市就是IP对应的城市
                if(city.equals(ip2CityBean.getCity()))
                {
                    //是IP请求的情况下
                    Log.i("IP反查-疫情","确认为通过IP访问，使用反查机制");
                    //ip2CityBean只能获取到城市的ID
                    //城市ID是一一对应的，但城市名不一定一一对应
                    //数据库有一个通过城市ID反查省的ID的函数，调用后获取省ID
                    //此时只能获取到DistrictCode,需要去数据库反查
                    String districtCode = ip2CityBean.getDistrictCode();
                    province_code = getProvinceCodeFromCityCode(districtCode);
                }
                else
                {
                    Log.i("IP反查-疫情","非IP获取信息，直接求取");
                    //调用反查省ID的代码
                    province_code =  String.valueOf(Objects.requireNonNull(FindProvince(city)).getProvinceId());
                }
                //Xutils请求上面八栏的接口
                //数据处理要去OnSuccess那
                loadData(url1);

            }
        };
        RetrofitHelper.getInstance().getIpFromCity(ip2CityBeanObserver);
    }

    /**
     * 显示进度对话框
     */
    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    /**
     * 关闭进度对话框
     */
    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        //孙奥界面要到city和province
        city = intent.getStringExtra("city");
        province = intent.getStringExtra("province");
        //一堆初始化
        setContentView(R.layout.activity_covid);
        covid_tv_city = findViewById(R.id.covid_tv_city);
        bentuquezhen = findViewById(R.id.covid_index_tv_addY);
        bentuwuzhengzhuang = findViewById(R.id.covid_index_tv_addN);
        jingwaishuru = findViewById(R.id.covid_index_tv_addI);
        siwangbingli = findViewById(R.id.covid_index_tv_addD);
        gaofengxiandiqu = findViewById(R.id.covid_index_tv_H);
        zhongfengxiandiqu = findViewById(R.id.covid_index_tv_M);
        xianyouquezhen = findViewById(R.id.covid_index_tv_N);
        leijiquezhen = findViewById(R.id.covid_index_tv_A);
        subao = findViewById(R.id.subao);
        //初始化完毕
        //下面的新闻列表，默认是一个空的列表
        itemsBeanList = new ArrayList<>();
        //CovidNewsBean.DataBean.ItemsBean itemsBean = new CovidNewsBean.DataBean.ItemsBean();

        //默认原本的ListView android:visibility="gone"
        //subao_empty_view是显示状态

        //如果没有数据，加载这个进度条页面
        subao.setEmptyView(findViewById(R.id.subao_empty_view));
        //初始化新闻的Adapter
        covid19NewsAdapter = new Covid19NewsAdapter(getBaseContext(),itemsBeanList);
        //设置Adapter，此时它列表为空，显示进度条
        subao.setAdapter(covid19NewsAdapter);
        //显示进度条
        showProgressDialog();
        //可以一起执行，加快加载速度
        //获取上面八个数据
        getCityFromIp();
        getCovidNews();
    }
    private void getCovidNews()
    {
        //设置URL
        //url2带的参数province_code并不是省ID，而是腾讯自己设置的
        String requesturl = url2 + getTencentNewsProvinceFromCity(province) + url3;
        //URL转请求对象
        RequestParams params = new RequestParams(requesturl);
        //xutils请求
        x.http().get(params, new CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析为covidNewsBean
                CovidNewsBean covidNewsBean = new Gson().fromJson(result, CovidNewsBean.class);
                //将它生成列表丢进去
                List<CovidNewsBean.DataBean.ItemsBean> covid = covidNewsBean.getData().getItems();
                itemsBeanList.addAll(covid);
                Log.i("疫情速报","疫情速报请求成功！");
                //更新adapter必须在UI线程进行
                runOnUiThread(new Runnable() {
                    public void run() {
                        //初始化的对象里的ItemsBeanList赋值
                        covid19NewsAdapter.setItemsBeanList(itemsBeanList);
                        //告知变化
                        covid19NewsAdapter.notifyDataSetChanged();
                        Log.i("疫情速报","疫情速报更新成功！");
                    }
                });
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("疫情速报","疫情速报请求失败！");
                ex.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                //完成的时候通知变化，注意这个通知必须写在UI的线程：

            }
        });


    }

    @Override
    public void onSuccess(String result){
        //到OnSuccess获取值
        CovidBean covidBean = new Gson().fromJson(result, CovidBean.class);
        if(covidBean!=null)
        {
            //因为第一个项是中国，所以直接获取第0号元素也即是中国的areaTree
            CovidBean.DataBean.Diseaseh5ShelfBean.AreaTreeBean areaTreeBean = covidBean
                    .getData()
                    .getDiseaseh5Shelf()
                    .getAreaTree().get(0);
            //取出每一个省
            for(CovidBean.DataBean.Diseaseh5ShelfBean.AreaTreeBean.ChildrenBean childrenBean:areaTreeBean.getChildren())
            {
                //如果省ID和传入的省ID一致
                if(childrenBean.getAdcode().equals(province_code))
                {
                    //本土确诊：local_confirm_add
                    //境外输入：abroad_confirm_add
                    //死亡病例：dead_add
                    //本土无症状：wzz_add
                    //高风险地区：highRiskAreaNum
                    //中风险地区：mediumRiskAreaNum
                    //现有确诊：nowCofirm
                    //累计确诊：confirm

                    //获取Today和Total的值
                    CovidBean.DataBean.Diseaseh5ShelfBean.AreaTreeBean.ChildrenBean.TodayBean todayBean = childrenBean.getToday();
                    CovidBean.DataBean.Diseaseh5ShelfBean.AreaTreeBean.ChildrenBean.TotalBean totalBean = childrenBean.getTotal();
                    //本土确诊（增加的量）
                    int bentu_quezhen = todayBean.getLocalConfirmAdd();
                    //境外输入（增加的量）
                    int jingwai_shuru = todayBean.getAbroadConfirmAdd();
                    //死亡病例(增加的量）
                    int siwang_bingli = todayBean.getDeadAdd();
                    //本土无症状（增加的量）
                    int bentu_wuzhengzhuang = todayBean.getWzzAdd();
                    //高风险地区
                    int gaofengxian_diqu = totalBean.getHighRiskAreaNum();
                    //中风险地区
                    int zhongfengxian_diqu = totalBean.getMediumRiskAreaNum();
                    //现有确诊
                    int xianyou_quezhen = totalBean.getNowConfirm();
                    //累计确诊
                    int leiji_quezhen = totalBean.getConfirm();
                    //设置省名
                    covid_tv_city.setText(childrenBean.getName());
                    //设置本土确诊 由于是增加量，所以前面要带加号
                    bentuquezhen.setText("+" + bentu_quezhen);
                    jingwaishuru.setText("+" + jingwai_shuru);
                    siwangbingli.setText("+" + siwang_bingli);
                    bentuwuzhengzhuang.setText(String.valueOf(bentu_wuzhengzhuang));
                    gaofengxiandiqu.setText(String.valueOf(gaofengxian_diqu));
                    zhongfengxiandiqu.setText(String.valueOf(zhongfengxian_diqu));
                    xianyouquezhen.setText(String.valueOf(xianyou_quezhen));
                    leijiquezhen.setText(String.valueOf(leiji_quezhen));
                    Log.i("疫情","疫情数据加载完毕！");
                    closeProgressDialog();
                    //加载疫情速报
                    break;
                }
            }
        }


    }
    @Override
    public void onError(Throwable ex, boolean isOnCallback){
        Log.i("IP反查-疫情","请求疫情出现问题");
        ex.printStackTrace();

    }


}