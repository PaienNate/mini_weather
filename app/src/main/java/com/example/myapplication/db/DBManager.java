package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.bean.CityBean;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    public static SQLiteDatabase database;
    /* 初始化数据库信息*/
    public static void initDB(Context context){
        DBHelper dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }
    /* 查找数据库当中城市列表*/
    public static List<String>queryAllCityName(){
        Cursor cursor = database.query("info", null, null, null, null, null,null);
        List<String>cityList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String city = cursor.getString(cursor.getColumnIndex("city"));
            cityList.add(city);
        }
        return cityList;
    }
    /* 根据城市名称，替换信息内容*/
    public static int updateInfoByCity(String city,String content){
        ContentValues values = new ContentValues();
        values.put("content",content);
        return database.update("info",values,"city=?",new String[]{city});
    }
    /* 新增一条城市记录*/
    public static long addCityInfo(String city,String content){
        ContentValues values = new ContentValues();
        values.put("city",city);
        values.put("content",content);
        return database.insert("info",null,values);
    }
    /* 根据城市名，查询数据库当中的内容*/
    public static String queryInfoByCity(String city){
        Cursor cursor = database.query("info", null, "city=?", new String[]{city}, null, null, null);
        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            String content = cursor.getString(cursor.getColumnIndex("content"));
            return content;
        }
        return null;
    }

    /* 存储城市天气要求最多存储5个城市的信息，一旦超过5个城市就不能存储了，获取目前已经存储的数量*/
    public static int getCityCount(){
        Cursor cursor = database.query("info", null, null, null, null, null, null);
        int count = cursor.getCount();
        return count;
    }

    /* 查询数据库当中的全部信息*/
    public static List<DatabaseBean>queryAllInfo(){
        Cursor cursor = database.query("info", null, null, null, null, null, null);
        List<DatabaseBean>list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String city = cursor.getString(cursor.getColumnIndex("city"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            DatabaseBean bean = new DatabaseBean(id, city, content);
            list.add(bean);
        }
        return list;
    }

    /* 根据城市名称，删除这个城市在数据库当中的数据*/
    public static int deleteInfoByCity(String city){
        return database.delete("info","city=?",new String[]{city});
    }

    /* 删除天气管理表当中所有的数据信息*/
    public static void deleteAllInfo(){
        String sql = "delete from info";
        database.execSQL(sql);
    }

    //添加：存储一个省
    public static void saveNewProvince(CityBean.Province province)
    {
            if (province != null) {
                ContentValues values = new ContentValues();
                //省名对应放入
                values.put("province_name", province.getName());
                //省对应的专门的Code放入
                values.put("province_code", province.getCode());
                //获取每一个province对应的City
                for(CityBean.Province.City city:province.getPchilds())
                {
                    //市名放入
                    ContentValues cityValue = new ContentValues();
                    //如果市名叫"直辖区“，把省名丢进去
                    if(city.getName().equals("市辖区"))
                    {
                        cityValue.put("city_name", province.getName());
                    }
                    else
                    {
                        cityValue.put("city_name", city.getName());
                    }

                    //市对应的专门的Code放入
                    cityValue.put("city_code", city.getCode());
                    //市对应的省的ID放入
                    cityValue.put("province_id",province.getCode());
                    //取出县
                    for(CityBean.Province.City.County county: city.getCchilds())
                    {
                        ContentValues countyValue = new ContentValues();
                        //县名放入
                        countyValue.put("county_name", county.getName());
                        //县ID放入
                        countyValue.put("county_code", county.getCode());
                        //县对应的市的ID
                        countyValue.put("city_id", city.getCode());
                        database.insert("County", null, countyValue);
                    }
                    database.insert("City", null, cityValue);
                }
                database.insert("Province", null, values);
            }
    }

    /**
     * 从数据库获取省份列表
     */
    public static List<String> getProvinceList()
    {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.query("Province", null, null,null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(cursor.getColumnIndex("province_name")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    /**
     * 从数据库获取省份
     */
    public static String getProvince(int code)
    {

        Cursor cursor = database.query("Province", null, "province_code = ?",
                new String[] { String.valueOf(code) }, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                return cursor.getString(cursor.getColumnIndex("province_name"));
            } while (cursor.moveToNext());
        }
        return "ERROR";
    }




/**
 * 从数据库模糊查询城市地区（用于搜索栏）
 */
public static List<CityBean.Province.City> loadSearchCities(String cityName)
{
    List<CityBean.Province.City> list = new ArrayList<CityBean.Province.City>();
    Cursor cursor = database.query("City", null, "city_name LIKE ?",
            new String[] { "%" + cityName + "%" }, null, null, null);
    if (cursor.moveToFirst()) {
        do {
            CityBean.Province.City city = new CityBean.Province.City();
            city.setId(cursor.getInt(cursor.getColumnIndex("id")));
            city.setName(cursor.getString(cursor
                    .getColumnIndex("city_name")));
            city.setCode(cursor.getString(cursor
                    .getColumnIndex("city_code")));
            city.setProvinceId(Integer.parseInt(cursor.getString(cursor
                    .getColumnIndex("province_id"))));
            list.add(city);
        } while (cursor.moveToNext());
    }
    cursor.close();
    return list;
}

    /**
     * 用于借助City反查Province的函数
     * @param cityName
     * @return
     */
    public static CityBean.Province.City FindProvince(String cityName)
    {
        Cursor cursor = database.query("City", null, "city_name = ?",
                new String[] { cityName }, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                CityBean.Province.City city = new CityBean.Province.City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setName(cursor.getString(cursor
                        .getColumnIndex("city_name")));
                city.setCode(cursor.getString(cursor
                        .getColumnIndex("city_code")));
                city.setProvinceId(Integer.parseInt(cursor.getString(cursor
                        .getColumnIndex("province_id"))));
                cursor.close();
                return city;
            } while (cursor.moveToNext());
        }
        return null;
    }




    /**
     * 从数据库读取某省下所有的城市信息。
     */
    public static List<CityBean.Province.City> loadCities(int provinceId) {
        List<CityBean.Province.City> list = new ArrayList<CityBean.Province.City>();
        Cursor cursor = database.query("City", null, "province_id = ?",
                new String[] { String.valueOf(provinceId) }, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                CityBean.Province.City city = new CityBean.Province.City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setName(cursor.getString(cursor
                        .getColumnIndex("city_name")));
                city.setCode(cursor.getString(cursor
                        .getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    /* 删除表当中所有的数据信息*/
    public static void deleteAllCity(){
        String sql = "delete from Province";
        String sql2 = "delete from City";
        String sql3 = "delete from County";
        database.execSQL(sql);
        database.execSQL(sql2);
        database.execSQL(sql3);
    }



}
