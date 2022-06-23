package com.example.myapplication.covid_19;

import java.util.HashMap;
import java.util.Map;

public class covidutil {
 //对智障的腾讯接口进行修正

 private static final Map<String, String> myMap = new HashMap<String, String>();
 //该列表抓包于腾讯的接口
 //并不清楚为什么会成这个样子。
 static {
  myMap.put("北京", "bj");
  myMap.put("内蒙古", "neimenggu");
  myMap.put("天津", "tj");
  myMap.put("上海", "sh");
  myMap.put("吉林", "jilin");
  myMap.put("广东", "gd");
  myMap.put("福建", "fj");
  myMap.put("辽宁", "ln");
  myMap.put("陕西", "xian");
  myMap.put("浙江", "zj");
  myMap.put("四川", "cd");
  myMap.put("山东", "sd");
  myMap.put("云南", "yn");
  myMap.put("重庆", "cq");
  myMap.put("湖南", "hn");
  myMap.put("江苏", "jiangsu");
  myMap.put("广西", "guangxi");
  myMap.put("湖北", "hb");
  myMap.put("河北", "hebei");
  myMap.put("山西", "shanxi");
  myMap.put("黑龙江", "heilongjiang");
  myMap.put("江西", "jiangxi");
  myMap.put("贵州", "guizhou");
  myMap.put("安徽", "ah");
  myMap.put("新疆", "xinjiang");
  myMap.put("甘肃", "gansu");
  myMap.put("河南", "henan");
  myMap.put("青海", "qinghai");
  myMap.put("海南", "hainan");
  myMap.put("西藏", "xizang");
  myMap.put("宁夏", "ningxia");
  myMap.put("台湾", "taiwan");
  myMap.put("香港", "hk");
  myMap.put("澳门", "macau");
 }

 public static String getTencentNewsProvinceFromCity(String provinceName) {
  for (String key : myMap.keySet()) {
   if (provinceName.contains(key)) {
    return myMap.get(key);
   }
  }

  return null;

 }





 }
