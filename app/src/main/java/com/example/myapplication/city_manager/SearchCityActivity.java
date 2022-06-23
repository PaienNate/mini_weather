package com.example.myapplication.city_manager;

import static com.example.myapplication.db.DBManager.FindProvince;
import static com.example.myapplication.db.DBManager.deleteAllCity;
import static com.example.myapplication.db.DBManager.getProvince;
import static com.example.myapplication.db.DBManager.getProvinceList;
import static com.example.myapplication.db.DBManager.loadSearchCities;
import static com.example.myapplication.db.DBManager.saveNewProvince;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.api.RetrofitHelper;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.CityBean;
import com.example.myapplication.bean.Ip2CityBean;
import com.example.myapplication.bean.WeatherBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SearchCityActivity extends BaseActivity implements View.OnClickListener {
    EditText searchEt;
    ImageView submitIv;
    GridView searchGv;
    RecyclerView searchList;
    TextView searchTv;
    //没有找到合适的热门城市接口,默认改一下改成“第一个是IP定位，剩下的是写死的热门”
    List<String> hotCitys = new ArrayList<>();
    private ArrayAdapter adapter;
    String url1 = "https://wis.qq.com/weather/common?source=pc&weather_type=observe|index|rise|alarm|air|tips|forecast_24h&province=";
    String url2 = "&city=";
    String city;
    String provice;
    List<String> stringList = new ArrayList<>();
    SearchListAdapter searchListAdapter;
    private List<CityBean.Province.City> cityBeanList = new ArrayList<>();
    private Observer<CityBean.Province> cityBeanObserver;
    private Observer<Ip2CityBean> ip2CityBeanObserver;
    private ProgressDialog progressDialog;
    private LinearLayoutManager linearLayoutManager;
    //添加Ip2CityBean，因为接口不同，导致有概率无法查找到对应的省
    private Ip2CityBean ip2CityBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //创建时
        super.onCreate(savedInstanceState);
        hotCitys.add("北京市");
        //设置基础View
        setContentView(R.layout.activity_search_city);
        //获取View
        searchEt = findViewById(R.id.search_et);
        submitIv = findViewById(R.id.search_iv_submit);
        searchGv = findViewById(R.id.search_gv);
        searchTv = findViewById(R.id.search_tv);
        searchList = findViewById(R.id.search_list);
        //加载进度条
        showProgressDialog();
        //加载CityList，做准备
        getCityList();
        //提升到最高
        searchList.bringToFront();
        //设置adapter和对应的数据（默认是空的）
        searchListAdapter = new SearchListAdapter(this, stringList);
        searchList.setAdapter(searchListAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        searchList.setLayoutManager(linearLayoutManager);
        //设置监听事件
        submitIv.setOnClickListener(this);
        //设置适配器 ArrayAdapter的使用场景是只用于每行只显示文本的情况。
        //设置adapter需要放在hotCitys获取完成之后
        adapter = new ArrayAdapter<>(this, R.layout.item_hotcity, hotCitys);
        //默认设置搜索栏不可见
        searchList.setVisibility(View.INVISIBLE);
        //为搜索添加监听事件
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            //修改之前，不用管
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //如果检测到输入框是空的
                if (TextUtils.isEmpty(searchEt.getText().toString())) {
                    //显示热门城市，隐藏搜索输入框
                    searchList.setVisibility(View.INVISIBLE);
                    searchGv.setVisibility(View.VISIBLE);
                    searchTv.setVisibility(View.VISIBLE);
                } else {
                    //隐藏热门城市列表，显示搜索框
                    searchGv.setVisibility(View.INVISIBLE);
                    searchTv.setVisibility(View.INVISIBLE);
                    searchList.setVisibility(View.VISIBLE);
                    //传输数据
                    stringList.clear();
                    for (CityBean.Province.City mycity : loadSearchCities(searchEt.getText().toString())) {
                        stringList.add(mycity.getName());
                    }
                    //默认每次搜索，都不会有很好的收益……
                    searchListAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        searchGv.setAdapter(adapter);
        setListener();
    }


    //刷新城市列表并存入到数据库
    private void refreshCityList() {
        //未能获取到数据库的情况
        //清空原本的数据库
        deleteAllCity();
        cityBeanObserver = new Observer<CityBean.Province>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(CityBean.Province province) {
                //数据库添加新成员
                saveNewProvince(province);
                //添加到数据库的时候，才真正意义上需要“province”
                //而我们这里仅仅需要城市的列表就够了。
                for (CityBean.Province.City city : province.getPchilds()) {
                    //设置CityId
                    city.setProvinceId(Integer.parseInt(province.getCode()));
                    //添加到列表
                    cityBeanList.add(city);
                }
            }

            @Override
            public void onError(Throwable e) {
                closeProgressDialog();
            }

            @Override
            public void onComplete() {
                //完成后续事件
                closeProgressDialog();
            }
        };
        RetrofitHelper.getInstance().getCityList(cityBeanObserver);
    }

    private void getCityFromIp()
    {
        ip2CityBeanObserver = new Observer<Ip2CityBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("IP反查","启动IP反查功能");
            }

            @Override
            public void onNext(Ip2CityBean cityBean) {
                Log.i("IP反查","获取到当前IP为" + cityBean.getCity());
                //赋值带上当前IP字样，方便后续处理
                //插入到最前面去
                hotCitys.add(0,"(IP)" + cityBean.getCity());
                //从此处赋值
                ip2CityBean = cityBean;

            }

            @Override
            public void onError(Throwable e) {
                Log.i("IP反查","出错，显示报错为");
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                //完成的时候，向adapter发送公告，更新IP所在
                Log.i("IP反查","IP反查成功，更新");
                adapter.notifyDataSetChanged();
            }
        };
        RetrofitHelper.getInstance().getIpFromCity(ip2CityBeanObserver);



    }




    //retrofit订阅者模式
    private void getCityList() {
        //判断是否第一次获取，如果不是第一次，直接返回数据库信息
        //当且仅当第一次/主动刷新时，进行数据刷新
        //IP获取每次都要获取，所以放在前面即可
        getCityFromIp();
        if (getProvinceList().isEmpty())
            refreshCityList();
        else {
            closeProgressDialog();
        }
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

    /* 设置监听事件*/
    private void setListener() {
        searchGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                city = hotCitys.get(position);
                //获取省份，由于开发时，未能考虑到两个接口获取的城市不一致，所以必须保留ip2City
                if(city.contains("(IP)"))
                {
                    provice = ip2CityBean.getProvince();
                    //去除无用的标识
                    city = city.replace("(IP)","");
                }
                else
                {
                    provice = GetProvice(city);
                }
                String url = url1 + provice + url2 + city;
                loadData(url);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_iv_submit:
                city = searchEt.getText().toString();
                if (!TextUtils.isEmpty(city)) {
//                      判断是否能够找到这个城市
                    try {
                        provice = GetProvice(city);
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(this, "您的输入非法", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    String url = url1 + provice + url2 + city;
                    loadData(url);
                } else {
                    Toast.makeText(this, "输入内容不能为空！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onSuccess(String result) {
        WeatherBean weatherBean = new Gson().fromJson(result, WeatherBean.class);
        if (weatherBean.getData().getIndex().getClothes() != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            city = provice + " " + city;
            Log.i("当前城市",city);
            intent.putExtra("city", city);
            startActivity(intent);
        } else {
            Toast.makeText(this, "暂时未收入此城市天气信息...", Toast.LENGTH_SHORT).show();
        }
    }

    private String GetProvice(String city) {
        //使用City模糊查询的部分，能够直接查到province_id，之后带着id去查询就可以了
        CityBean.Province.City tempcity = FindProvince(city);
        assert tempcity != null;
        return getProvince(tempcity.getProvinceId());
    }
}
