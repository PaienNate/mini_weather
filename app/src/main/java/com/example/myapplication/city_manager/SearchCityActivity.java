package com.example.myapplication.city_manager;

import static com.example.myapplication.db.DBManager.FindProvince;
import static com.example.myapplication.db.DBManager.deleteAllCity;
import static com.example.myapplication.db.DBManager.getProvince;
import static com.example.myapplication.db.DBManager.getProvinceList;
import static com.example.myapplication.db.DBManager.loadCities;
import static com.example.myapplication.db.DBManager.loadSearchCities;
import static com.example.myapplication.db.DBManager.saveNewProvince;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
    //没有找到合适的热门城市接口,暂时写死
    String[] hotCitys = {"北京市"};
    private ArrayAdapter adapter;
    String url1 = "https://wis.qq.com/weather/common?source=pc&weather_type=observe|index|rise|alarm|air|tips|forecast_24h&province=";
    String url2 = "&city=";
    String city;
    String provice;
    List<String> test = new ArrayList<>();
    SearchListAdapter searchListAdapter;
    private List<CityBean.Province.City> cityBeanList = new ArrayList<>();
    private Observer<CityBean.Province> cityBeanObserver;
    private ProgressDialog progressDialog;
    Context thesecon = this;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //创建时
        super.onCreate(savedInstanceState);
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
        getCityList();

        //提升到最高
        searchList.bringToFront();
        //设置adapter和对应的数据（默认是空的）
        searchListAdapter = new SearchListAdapter(this, test);
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
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//修改之前，不用管
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
                    test.clear();
                    for (CityBean.Province.City mycity : loadSearchCities(searchEt.getText().toString())) {
                        test.add(mycity.getName());
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

    //retrofit订阅者模式
    private void getCityList() {
        //判断是否第一次获取，如果不是第一次，直接返回数据库信息
        //当且仅当第一次/主动刷新时，进行数据刷新
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
                city = hotCitys[position];
//                获取省份
                provice = GetProvice(city);
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
                    provice = GetProvice(city);
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
