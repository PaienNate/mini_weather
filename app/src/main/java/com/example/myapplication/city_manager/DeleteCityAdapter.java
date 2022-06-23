package com.example.myapplication.city_manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapplication.R;
import java.util.List;

public class DeleteCityAdapter extends BaseAdapter{
    Context context;
    List<String>mDatas;
//    表示要删除的城市记录
    List<String>deleteCitys;

//    通过构造方法传递数据
    public DeleteCityAdapter(Context context, List<String> mDatas,List<String>deleteCitys) {
        this.context = context;
        this.mDatas = mDatas;
        this.deleteCitys = deleteCitys;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }
//返回指定位置的数据
    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }
//返回位置
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
//        判断是否有能够复用的coverView
        if (convertView == null) {
//            通过布局管理器生成
            convertView = LayoutInflater.from(context).inflate(R.layout.item_deletecity,null);
//            生成holder对象
            holder = new ViewHolder(convertView);
//            设置标记
            convertView.setTag(holder);
        }else{
//            获取holder对象
            holder = (ViewHolder) convertView.getTag();
        }
//        获取指定位置所对应的数据源（city名称）
        final String city = mDatas.get(position);
//        设置到TextView上
        holder.tv.setText(city);
//        ImageView删除操作
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatas.remove(city);
//                想删除的记录到deleteCitys中
                deleteCitys.add(city);
                notifyDataSetChanged();  //删除了提示适配器更新（暂时删除）
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView tv;
        ImageView iv;
        public ViewHolder(View itemView){
            tv = itemView.findViewById(R.id.item_delete_tv);
            iv = itemView.findViewById(R.id.item_delete_iv);
        }
    }
}
