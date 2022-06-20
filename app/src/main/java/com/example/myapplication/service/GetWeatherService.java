package com.example.myapplication.service;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.bean.WidgetEntity;
import com.example.myapplication.widget.UpdateWidget;
import com.example.myapplication.widget.mvp.presenter.GridInfoPresenter;
import com.example.myapplication.widget.mvp.view.GridInfoView;

//创建小组件第三步：实现一个更新数据的服务，并在需要的时候启动该服务。
//注意这里除了扩写了Service，还实现了GridInfoView。
public class GetWeatherService extends Service implements GridInfoView {
    //使用MVP方式获取数据
    private GridInfoPresenter gridInfoPresenter;
    private int[] appWidgetIds;
    private AppWidgetManager appWidgetManager;
    private Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //拿到更新所需要的内容：appWidgetIds;appWidgetManager;
        appWidgetIds = intent.getIntArrayExtra("appWidgetIds");
        appWidgetManager = AppWidgetManager.getInstance(GetWeatherService.this);
        //利用MVP模式，下载数据。备注：如果对MVP不是很了解的话，也可以使用自己的请求方式请求数据即可。
        //另：如果你想学习MVP模式的话：可前往“https://github.com/GodDavide/MVP”查看MVP模式介绍，感谢您的支持。
        Toast.makeText(GetWeatherService.this, "正在加载最新数据，请稍等... ...", Toast.LENGTH_SHORT).show();
        gridInfoPresenter = new GridInfoPresenter(this);
        gridInfoPresenter.loadWidgetInfo();
        //初始化Widget（此时Gridview还没有最新数据，如果是首次创建的话，数据为空，非首次，显示的是上次请求的数据。数据存储在数据库里）
        //为了方便，将更新方法直接写进构造函数里了

        //创建第四步：实现对小组件的View更新服务

        new UpdateWidget(GetWeatherService.this, appWidgetIds, appWidgetManager);
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //当数据下载完成后，返回信息，因为数据源发生了改变，所以直接调用：notifyAppWidgetViewDataChanged  方法；
                //WidgetGridService将自动执行:onDataSetChanged()方法，然后，从新对Gridview进行赋值刷新。
                if (msg.what == 100) {
                    appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_grid);
                }
            }
        };

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void loadWidgetInfo(WidgetEntity widgetEntity) {
        //利用MVP模式下载数据，下载成功后，首先更新数据库，然后，通过Handler返回信息，执行刷新Widget功能。
        if (widgetEntity != null) {
            //首先，清空数据库，然后，将新数据添加进去。
            //进行数据库操作，目前不需要进行这些操作
            //MyApplication.getMyApp().getDbUtils().deleteAll(WidgetEntity.NewslistBean.class);
            //List<WidgetEntity.NewslistBean> all = MyApplication.getMyApp().getDbUtils().findAll(WidgetEntity.NewslistBean.class);
            //for (int i = 0; i < widgetEntity.getNewslist().size(); i++) {
            //    String picUrl = widgetEntity.getNewslist().get(i).getPicUrl();
            //    MyApplication.getMyApp().getDbUtils().saveOrUpdate(widgetEntity.getNewslist().get(i));
            //}
            Message obtain = Message.obtain();
            obtain.what = 100;
            try
            {
                handler.sendMessage(obtain);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void loadFailed(String s) {
        Toast.makeText(GetWeatherService.this, "数据请求失败,请稍后重试... ...", Toast.LENGTH_SHORT).show();
    }
}
