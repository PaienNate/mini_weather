package com.example.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

    /**
     *  Province表建表语句
     */
    public static final String CREATE_PROVINCE = "create table Province ("
            + "id integer primary key autoincrement, "
            + "province_name text, "
            + "province_code text)";
    /**
     *  City表建表语句
     */
    public static final String CREATE_CITY = "create table City ("
            + "id integer primary key autoincrement, "
            + "city_name text, "
            + "city_code text, "
            + "province_id integer)";
    /**
     *  County表建表语句
     */
    public static final String CREATE_COUNTY = "create table County ("
            + "id integer primary key autoincrement, "
            + "county_name text, "
            + "county_code text, "
            + "city_id integer)";


    public DBHelper(Context context){
        super(context,"forecast.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
//        创建天气表的操作
        String sql = "create table info(_id integer primary key autoincrement,city varchar(20) unique not null,content text not null)";
        db.execSQL(sql);
        //创建城市部分
        db.execSQL(CREATE_PROVINCE); //创建Province表
        db.execSQL(CREATE_CITY); //创建City表
        db.execSQL(CREATE_COUNTY); //创建County表
        //Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
