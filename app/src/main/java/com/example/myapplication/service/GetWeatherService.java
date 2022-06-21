package com.example.myapplication.service;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.widget.UpdateWidget;

//创建小组件第三步：实现一个更新数据的服务，并在需要的时候启动该服务。
//注意这里除了扩写了Service，还实现了GridInfoView。
public class GetWeatherService extends Service{
    private int[] appWidgetIds;
    private AppWidgetManager appWidgetManager;
    private static Handler handler;
    public static void notifyDatabaseUpdate()
    {
        if(handler!=null)
        {
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
        else
        {
            Log.i("小组件","失败，大概率因为小组件未创建");
        }
    }

    //一开始就创建handler
    @Override
    public void onCreate() {
        Log.i("小组件部分","小组件加载Handler新建");
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
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //拿到更新所需要的内容：appWidgetIds;appWidgetManager;
        appWidgetIds = intent.getIntArrayExtra("appWidgetIds");
        appWidgetManager = AppWidgetManager.getInstance(GetWeatherService.this);
        Toast.makeText(GetWeatherService.this, "正在加载最新数据，请稍等... ...", Toast.LENGTH_SHORT).show();
        //将自己作为GridInfoView传入到Presenter内
        //初始化Widget（此时Gridview还没有最新数据，如果是首次创建的话，数据为空，非首次，显示的是上次请求的数据。数据存储在数据库里）
        //为了方便，将更新方法直接写进构造函数里了
        new UpdateWidget(GetWeatherService.this, appWidgetIds, appWidgetManager);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
