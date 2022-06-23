package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication.db.DBManager;


public class MoreActivity extends AppCompatActivity implements View.OnClickListener{
    TextView bgTv,cacheTv,versionTv,shareTv;
    RadioGroup exbgRg;
    ImageView backIv;
    private SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        bgTv = findViewById(R.id.more_tv_exchangebg);
        cacheTv = findViewById(R.id.more_tv_cache);
        versionTv = findViewById(R.id.more_tv_version);
        shareTv = findViewById(R.id.more_tv_share);

        backIv = findViewById(R.id.more_iv_back);
        exbgRg = findViewById(R.id.more_rg);

        bgTv.setOnClickListener(this);
        cacheTv.setOnClickListener(this);
        shareTv.setOnClickListener(this);
        backIv.setOnClickListener(this);

        pref = getSharedPreferences("bg_pref", MODE_PRIVATE);
        String versionName = getVersionName();
        versionTv.setText("当前版本:    v"+versionName);
        setRGListener();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //判断事件完成，就是选择完图片
        //原本的换壁纸，跳转到主页面
        SharedPreferences.Editor editor = pref.edit();
        Intent intent = new Intent(MoreActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

        super.onActivityResult(requestCode, resultCode, data);
        //如果选中一张图，那么Activity返回OK
        if (resultCode == Activity.RESULT_OK) {
            //根据网上的获取方法获取到图片路径
            Uri uri = data.getData();
            Cursor cursor = this.getContentResolver().query(uri, null, null,
                    null, null);
            cursor.moveToFirst();
            //CSDN：
            //path就是用户选择文件的路径啦
            // 至于参数为什么是1，这是我尝试的经验
            // 拿到路径后你就可以调用那张图片显示给用户看或者做别的事
            String path = cursor.getString(1);
            //图片的序号
            editor.putInt("bg",99);
            //图片的地址
            editor.putString("path",path);
            editor.commit();
            //Intent带参数启动MainActivity
            startActivity(intent);
        }
        else
        {
            //没有选择图的情况下
            Toast.makeText(MoreActivity.this,"您未选择任何背景！",Toast.LENGTH_SHORT).show();
        }
    }


    private void setRGListener() {
        /* 设置改变背景图片的单选按钮的监听*/
        exbgRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                获取目前的默认壁纸
                int bg = pref.getInt("bg", 0);
                SharedPreferences.Editor editor = pref.edit();
                Intent intent = new Intent(MoreActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                //根据选中的ID执行：
                switch (checkedId) {
                    case R.id.more_rb_green:
                        if (bg==0) {
                            Toast.makeText(MoreActivity.this,"您选择的为当前背景，无需改变！",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        editor.putInt("bg",0);
                        editor.commit();
                        break;
                    case R.id.more_rb_pink:
                        if (bg==1) {
                            Toast.makeText(MoreActivity.this,"您选择的为当前背景，无需改变！",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        editor.putInt("bg",1);
                        editor.commit();
                        break;
                    case R.id.more_rb_blue:
                        if (bg==2) {
                            Toast.makeText(MoreActivity.this,"您选择的为当前背景，无需改变！",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        editor.putInt("bg",2);
                        editor.commit();
                        break;
                        //增加的项
                    case R.id.more_myself:
                        //用户可以换不同的壁纸，所以没有当前壁纸检测
                        //由于这个和另外几个的原理不同，不能startActivity在这里，否则就会出问题。
                        //去寻找是否已经有了权限
                       int a =  ContextCompat.checkSelfPermission(MoreActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE);
                       //判断是否有读的权限，PERMISSION_GRANTED有权限，否则就是没有
                       // 请求权限的第一步是声明这个程序要能请求该权限，定义在AndroidManifest.xml内
                       if(ContextCompat.checkSelfPermission(MoreActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                            {
                                //Intent调用选择器（只能选择图片）
                                //选择图片要调用外置存储，外置存储需要请求权限
                                Intent myintent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                //请求需要得到返回值，请求的Code = 1
                                //startActivityForResult的返回值需要在Activity内重写onActivityResult来接收传值
                                startActivityForResult(myintent, 1);
                                return;
                            }
                            else
                            {
                                //如果没有权限，请求对应的权限
                                ActivityCompat.requestPermissions(MoreActivity.this,new String[]{
                                        Manifest.permission.READ_EXTERNAL_STORAGE},1);
                                return;
                            }
                            //Toast.makeText(MainActivity.this,"您申请了动态权限",Toast.LENGTH_SHORT).show();
                            //自己选就不用估计上面bg是啥了
                            //另外：这里的选取值要去上面进行处理

                }
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.more_iv_back:
                finish();
                break;
            case R.id.more_tv_cache:
                clearCache();
                break;
            case R.id.more_tv_share:
                shareSoftwareMsg("mini天气系统app是一款超萌超可爱的天气预报软件，画面简约，播报天气情况非常精准，快来下载吧！");
                break;
            case R.id.more_tv_exchangebg:
                if (exbgRg.getVisibility() == View.VISIBLE) {
                    exbgRg.setVisibility(View.GONE);
                }else{
                    exbgRg.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    private void shareSoftwareMsg(String s) {
        /* 分享软件的函数*/
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,s);
        startActivity(Intent.createChooser(intent,"mini天气系统"));
    }

    private void clearCache() {
        /* 清除缓存的函数*/
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示信息").setMessage("确定要删除所有缓存么？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBManager.deleteAllInfo();
                Toast.makeText(MoreActivity.this,"已清除全部缓存！",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MoreActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }).setNegativeButton("取消",null);
        builder.create().show();
    }

    public String getVersionName() {
        /* 获取应用的版本名称*/
        PackageManager manager = getPackageManager();
        String versionName = null;
        try {
            PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
