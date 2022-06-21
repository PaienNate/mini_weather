package com.example.myapplication.widget;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.widget.config.WidgetAction;


public class WeatherWidgetActivity extends AppCompatActivity {

    private ImageView showIv;
    private TextView showTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //创建Activity
        super.onCreate(savedInstanceState);
        //设置显示
        setContentView(R.layout.activity_widget);
        //初始化
        showIv = (ImageView) findViewById(R.id.main_iv);
        showTv = (TextView) findViewById(R.id.main_tv);
        //获取Widget的部分
        Intent intent = getIntent();
        getWidgetInfo(intent);
    }

    private void getWidgetInfo(Intent intent) {
        //对于这个Activity，查看小组件传值过来是让干嘛
        switch (intent.getAction()) {
            case WidgetAction.GOD_GRID:
                //接口中有图片网址，就顺便让大家去看看吧
                String url = intent.getStringExtra("url");
                Uri uri = Uri.parse(url);
                Intent intentWeb = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intentWeb);
                break;
                //如果是一开始
            case WidgetAction.GOD_START_ACTIVITY:
                //给它去掉然后再设置可见
                showTv.setVisibility(View.GONE);
                showIv.setVisibility(View.VISIBLE);
                //设置一个点击事件，用来刷新
                showIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showIv.setImageResource(R.drawable.refreshing);
                    }
                });
                break;
        }

    }
}
