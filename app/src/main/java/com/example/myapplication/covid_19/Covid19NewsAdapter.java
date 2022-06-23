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
            // 可以在这里通过文件名来判断，是否本地有此图片
            drawable = Drawable.createFromStream(
                    new URL(imageUrl).openStream(), "image.jpg");
        } catch (IOException e) {
            Log.d("test", e.getMessage());
        }
        if (drawable == null) {
           // Log.d("test", "null drawable");
        } else {
           // Log.d("test", "not null drawable");
        }

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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_covid,null);
            holder = new Covid19NewsAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
            String subao_title = itemsBeanList.get(position).getTitle();
            String subao_pic = itemsBeanList.get(position).getShortcut();
            String subao_source = itemsBeanList.get(position).getSrcfrom();
            String subao_url = itemsBeanList.get(position).getNewsUrl();
            ViewHolder finalHolder = holder;
            new Thread(new Runnable(){
                @Override
                public void run() {
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
            holder.tv.setText(subao_title);
            holder.source.setText(subao_source);
            //绑定点击事件
            holder.relative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("点击事件","开始点击事件");
                    Uri uri = Uri.parse(subao_url);
                    Intent intentWeb = new Intent(Intent.ACTION_VIEW, uri);
                    //Calling startActivity() from outside of an Activity
                    // context requires the FLAG_ACTIVITY_NEW_TASK flag.
                    // Is this really what you want? Yes!
                    intentWeb.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
