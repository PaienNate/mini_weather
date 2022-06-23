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
                //赋值带上当前IP字样，方便后续处理
                //插入到最前面去
                //hotCitys.add(0,"(IP)" + cityBean.getCity());
                //从此处赋值
                ip2CityBean = cityBean;
                //使用Handler等待值
            }

            @Override
            public void onError(Throwable e) {
                Log.i("IP反查-疫情","出错，显示报错为");
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.i("IP反查-疫情","IP反查成功，更新");
                //反查成功，检查其是否和传入的城市相同
                if(city.equals(ip2CityBean.getCity()))
                {
                    Log.i("IP反查-疫情","确认为通过IP访问，使用反查机制");
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
                //使用这个ID,然后去OnSuccess那获取值
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
        city = intent.getStringExtra("city");
        province = intent.getStringExtra("province");
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
        itemsBeanList = new ArrayList<>();
        CovidNewsBean.DataBean.ItemsBean itemsBean = new CovidNewsBean.DataBean.ItemsBean();
        //设置没有数据的时候加载的页面
        subao.setEmptyView(findViewById(R.id.subao_empty_view));
        covid19NewsAdapter = new Covid19NewsAdapter(getBaseContext(),itemsBeanList);
        subao.setAdapter(covid19NewsAdapter);
        //首先获取当前的城市
        showProgressDialog();
        getCityFromIp();
        getCovidNews();
    }
    private void getCovidNews()
    {
        String requesturl = url2 + getTencentNewsProvinceFromCity(province) + url3;
        RequestParams params = new RequestParams(requesturl);
        x.http().get(params, new CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析为covidNewsBean
                CovidNewsBean covidNewsBean = new Gson().fromJson(result, CovidNewsBean.class);
                //将它生成列表丢进去
                List<CovidNewsBean.DataBean.ItemsBean> covid = covidNewsBean.getData().getItems();
                itemsBeanList.addAll(covid);
                Log.i("疫情速报","疫情速报请求成功！");
                runOnUiThread(new Runnable() {
                    public void run() {
                        covid19NewsAdapter.setItemsBeanList(itemsBeanList);
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
            //因为其只有一个子项，所以可以直接获取第0号元素
            CovidBean.DataBean.Diseaseh5ShelfBean.AreaTreeBean areaTreeBean = covidBean
                    .getData()
                    .getDiseaseh5Shelf()
                    .getAreaTree().get(0);
            for(CovidBean.DataBean.Diseaseh5ShelfBean.AreaTreeBean.ChildrenBean childrenBean:areaTreeBean.getChildren())
            {
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
                    //设置城市名
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