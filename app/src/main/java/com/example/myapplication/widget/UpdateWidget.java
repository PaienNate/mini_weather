package com.example.myapplication.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.myapplication.R;
import com.example.myapplication.city_manager.CityManagerActivity;
import com.example.myapplication.service.GetWeatherService;
import com.example.myapplication.service.WidgetGridService;


//第五步：实现小组件更新的类
public class UpdateWidget {
    private RemoteViews remoteViews;
    private Context context;
    private int[] appWidgetIds;
    private AppWidgetManager appWidgetManager;
    //初始化小组件，以及remoteViews
    public UpdateWidget(Context context, int[] appWidgetIds, AppWidgetManager appWidgetManager) {
        this.context = context;
        this.appWidgetIds = appWidgetIds;
        this.appWidgetManager = appWidgetManager;
        this.remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_widget);
        //为了方便，在创建UpdateWidget实例的时候，直接调用UpdateWidgetView();
        UpdateWidgetView();

    }

    public void UpdateWidgetView() {
        // 这是小组件自己的ID
        for (int appWidgetId : appWidgetIds) {
            /**
             *  刷新按钮的点击事件,通过Intent启动服务，PendingIntent.getSwevice();
             */
            //如果点击刷新按钮，我们就要重新启动GetWeatherService来获取最新信息。
            //这两个是预备给刷新按钮来进行绑定的
            Intent intent = new Intent(context, GetWeatherService.class);
            //使用Intent提供了小组件ID
            intent.putExtra("appWidgetIds", appWidgetIds);
            //使用PendingIntent,第四个参数表明是对pendingIntent的更新，是对intent的封装
            PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            //给标题和刷新按钮都设置上这个参数。到时候点击上去就相当于请求刷新了
            remoteViews.setOnClickPendingIntent(R.id.widget_title, pendingIntent);
            remoteViews.setOnClickPendingIntent(R.id.widget_refresh, pendingIntent);
            /**
             *  加载Gridview
             */
            //加载下面的Grid列表
            showGridInfo(appWidgetId);

            /**
             *  最后，刷新remoteViews
             */
            //最后对整个进行更新
            appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
        }
    }

    private void showGridInfo(int appWidgetId) {
        //由于Grid是一个集合，我们必须得为他绑定Service，否则无法修改其中的内容
        //这也是开发部分的难点
        //绑定Intent为Grid集合使用的Service
        Intent intent = new Intent(context, WidgetGridService.class);
        //提供给它一个额外的APPWIDGET_ID参数，以及总列表
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        //设置其绑定的布局为widget_grid.xml，以及操作对应的上面的Service
        remoteViews.setRemoteAdapter(R.id.widget_grid, intent);
        //由于一开始这个数据定然不会加载，需要等待数据到手，所以先绑定一个默认页，也就是widget_empty_view
        //有了数据之后，它会自动去掉
        remoteViews.setEmptyView(R.id.widget_grid, R.id.widget_empty_view);
        //设定一个去主Activity的Intent
        Intent intentEvent = new Intent(context, CityManagerActivity.class);
        //在到了之后销毁之前的Activity
        intentEvent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //设置PendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intentEvent, PendingIntent.FLAG_UPDATE_CURRENT);
        //对于其中的集合，onClickPendingIntent()需要用setPendingIntentTemplate进行替换
        remoteViews.setPendingIntentTemplate(R.id.widget_grid, pendingIntent);
        //最后更新一次显示（此时显示出来的是默认页，因为数据还没到）
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

}
