package com.example.myapplication.covid_19;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.bean.CovidNewsBean;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Covid19NewsAdapter extends BaseAdapter {
    Context context;

    List<CovidNewsBean.DataBean.ItemsBean> itemsBeanList;

    //专门写的Set方法
    public void setItemsBeanList(List<CovidNewsBean.DataBean.ItemsBean> itemsBeanList) {
        this.itemsBeanList = itemsBeanList;
    }

    public Covid19NewsAdapter(Context context, List<CovidNewsBean.DataBean.ItemsBean> itemsBeanList) {
        this.context = context;
        this.itemsBeanList = itemsBeanList;
    }

    private Drawable loadImageFromNetwork(String imageUrl)
    {
        Drawable drawable = null;
        try {
            drawable = Drawable.createFromStream(
                    new URL(imageUrl).openStream(), "image.jpg");
        } catch (IOException e) {
            Log.d("test", e.getMessage());
        }
//        if (drawable == null) {
//           // Log.d("test", "null drawable");
//        } else {
//           // Log.d("test", "not null drawable");
//        }

        return drawable ;
    }

    @Override
    public int getCount() {
        return itemsBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Covid19NewsAdapter.ViewHolder holder = null;
        //网上教的，可以复用
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_covid,null);
            holder = new Covid19NewsAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
            //初始化
            String subao_title = itemsBeanList.get(position).getTitle();
            String subao_pic = itemsBeanList.get(position).getShortcut();
            String subao_source = itemsBeanList.get(position).getSrcfrom();
            String subao_url = itemsBeanList.get(position).getNewsUrl();
            ViewHolder finalHolder = holder;
            //创建新线程去给图片赋值
            new Thread(new Runnable(){
                @Override
                public void run() {
                    //用drawable的获取方式读取图片链接为drawable对象
                    Drawable drawable = loadImageFromNetwork(subao_pic);
                    // post() 特别关键，就是到UI主线程去更新图片
                    finalHolder.iv.post(new Runnable(){
                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            finalHolder.iv.setImageDrawable(drawable);
                            //提示重新加载数据，因为数据变了
                            notifyDataSetChanged();

                        }}) ;
                }
            }).start();
            //设置标题，来源
            holder.tv.setText(subao_title);
            holder.source.setText(subao_source);
            //绑定点击事件写在convertView == null里，点击的行为逻辑就正常了
            holder.relative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("点击事件","开始点击事件");
                    //把链接转成uri
                    Uri uri = Uri.parse(subao_url);
                    //通过ACTION_VIEW的方式，打开浏览器去查看这个页面
                    Intent intentWeb = new Intent(Intent.ACTION_VIEW, uri);
                    //因为不是在Activity内请求的，需要增加FLAG_ACTIVITY_NEW_TASK这个Flag
                    intentWeb.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //启动Activity
                    context.startActivity(intentWeb);
                }
            });
        }else{
            holder = (Covid19NewsAdapter.ViewHolder) convertView.getTag();
            //Log.i("重用","重用111");
        }
        return convertView;
    }


    class ViewHolder{
        TextView tv;
        ImageView iv;
        TextView source;
        RelativeLayout relative;
        public ViewHolder(View itemView){
            tv = itemView.findViewById(R.id.item_subao_tv);
            iv = itemView.findViewById(R.id.item_subao_iv);
            source = itemView.findViewById(R.id.item_subao_source);
            relative = itemView.findViewById(R.id.item_subao_rl_ti);
        }
    }
}
