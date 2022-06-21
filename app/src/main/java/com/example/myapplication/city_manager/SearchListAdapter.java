package com.example.myapplication.city_manager;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder> {
    public View searchView;

    public void notifyItemChanged() {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //public TextView title;
        public TextView tvContent;
        public EditText searchEt;
        public ImageView submitIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tvContent);
        }
    }


    //通用的Map - 修改为SearchCityActivity
    private List<String> mDatas;
    private final SearchCityActivity context;


    //构造时传Context也就是activity，在下面转成View
    public SearchListAdapter(SearchCityActivity context, List<String> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_citylist,null);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        viewHolder.tvContent.setText(mDatas.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将值传好
                Toast.makeText(context, "您选择了："+mDatas.get(i), Toast.LENGTH_SHORT).show();
                //首先显示是哪个，然后再设置，不然就会引发变动
                context.searchEt.setText(mDatas.get(i));
            }
        });
    }
    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}