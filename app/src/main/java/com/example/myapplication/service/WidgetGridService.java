package com.example.myapplication.service;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.myapplication.R;
import com.example.myapplication.bean.WidgetEntity;
import com.example.myapplication.widget.utils.LruCacheCallBack;

import java.util.ArrayList;
import java.util.List;

//对于小组件内的数据，如何处理的问题
//首先需要扩写RemoteViewsService
public class WidgetGridService extends RemoteViewsService {
    //重写获取ViewFactory方法，让它获取的是下面我们自定义的这个类
    String testpic = "https://dss2.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/weather/icons2/a1.png";
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridRemoteViewFactory(this, intent);
    }
    //实现了RemoteViewsFactory的类
    private class GridRemoteViewFactory implements RemoteViewsFactory {
        //得到的上下文，对应的ID和一个实体类
        private Context context;
        private int appWidgetId;
        private List<WidgetEntity.NewslistBean> newslist = new ArrayList<>();
        //初始化，获取到对应的小组件ID
        public GridRemoteViewFactory(Context context, Intent intent) {
            this.context = context;
            Log.i("EXTRA_APPWIDGET_ID",AppWidgetManager.EXTRA_APPWIDGET_ID);
            Log.i("INVALID_APPWIDGET_ID",AppWidgetManager.EXTRA_APPWIDGET_ID);
            this.appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        /**
         * @author David  created at 2016/8/11 17:41
         * 首次执行，初始化数据时执行onCreate();数据是从数据库里拿的。
         */
        //首次执行的时候从数据库里取出数据。
        //其应该有其他方式设置数据源，但是目前我只能通过这种方式获取数据
        @Override
        public void onCreate() {
            Log.i("模拟请求检测","onCreate，添加一些数据。");
            for(int i=0;i<10;i++)
            {
                WidgetEntity.NewslistBean newslistBean = new WidgetEntity.NewslistBean();
                newslistBean.setCtime("2016-06-07");
                newslistBean.setDescription("我是测试的,序号为" + i);
                newslistBean.setTitle("测试序号为" + i);
                newslistBean.setPicUrl(testpic);
                newslistBean.setUrl("http://www.baidu.com");
                //暂时模拟添加数据
                newslist.add(newslistBean);
            }
        }

        /**
         * @author David  created at 2016/8/11 17:41
         * 当数据源发生变化时，AppWidgetManager调用了 notifyAppWidgetViewDataChanged();方法时执行
         */
        //数据源发生变化的时候，就是AppWidgetManager调用方法告知的时候，需要执行这个函数
        //一般来说，写成和原本一致即可。
        @Override
        public void onDataSetChanged() {
            //这里只能去数据库要数据
            Log.i("模拟请求检测","onDataSetChanged，没有新数据。");
        }

        /**
         * @author David  created at 2016/8/11 17:42
         * 销毁时，情况数据源
         */
        @Override
        public void onDestroy() {
            newslist.clear();
        }

        /**
         * @author David  created at 2016/8/11 17:43
         * 返回Gridview的Item条目数
         */
        @Override
        public int getCount() {
            return newslist.size();
        }

        /**
         * @author David  created at 2016/8/11 17:43
         * 给Gridview设置数据
         */
        //从里面取出数据后，将数据设置给GridView集合，此时数据就安全到达了。
        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.item_grid);
            if (newslist != null) {
                WidgetEntity.NewslistBean newslistBean = newslist.get(position);
                /**
                 * @author David  created at 2016/8/11 13:52
                 * remoteViews.setImageViewUri(R.id.item_image, Uri.parse(newslistBean.getPicUrl()));
                 *  这个方法不知道为什么总是设置不上图片，于是改用setImageViewBitmap();
                 *
                 *  备注：此处加了图片的三级缓存，如果不需要的话，可直接调用
                 *  URL picUrl  = new URL(key);
                 *  Bitmap  bitmap = BitmapFactory.decodeStream(picUrl.openStream());
                 *  可拿到bitmap;
                 */
                Bitmap bitmap = LruCacheCallBack.bitmapCallMap(newslistBean.getPicUrl(), context);
                if (bitmap != null) {
                    remoteViews.setImageViewBitmap(R.id.item_image, bitmap);
                }
                remoteViews.setTextViewText(R.id.item_text, newslistBean.getTitle());

                //给item设置响应事件
                Intent intent = new Intent();
                intent.putExtra("url", newslistBean.getUrl());
                remoteViews.setOnClickFillInIntent(R.id.item_widget, intent);
            }
            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        /**
         * @author David  created at 2016/8/11 17:46
         * 无特殊要求，返回的View类型数 ==1；
         */
        @Override
        public int getViewTypeCount() {
            return 1;
        }

        /**
         * @author David  created at 2016/8/11 17:47
         * 返回item的id
         */
        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
