package com.example.myapplication.widget.mvp.model;


import com.example.myapplication.bean.WidgetEntity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David  create on 2016/8/10  19:09.
 * @email david.forever.god@gmail.com
 * Learn from yesterday, live for today, hope for tomorrow.
 */
public class GridInfoModelImpl implements GridInfoModel {
    @Override
    public void loadWidgetInfo(final OnLoadWidgetInfoListener onLoadWidgetInfoListener) {
        /**
         * @author  David  created at 2016/8/11 9:53
         *  测试接口使用的是百度ApiSore免费接口
         */
        //使用本地JSON代替免费接口，先跑起来看看是个啥情况
        String testpic = "https://dss2.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/weather/icons2/a1.png";
        String teststr = "{\"code\":200,\"msg\":\"success\",\"newslist\":[{\"ctime\":\"2016-06-07\",\"title\":\"美国队长在中国的话，会不会拍成这样？\",\"description\":\"奔波儿灞与灞波儿奔\",\"picUrl\":\"http://t1.qpic.cn/mblogpic/f01a972dbcc1060fd456/2000\",\"url\":\"http://mp.weixin.qq.com/s?__biz=MjM5NzEyMTUxNg==&idx=5&mid=2655198291&sn=c17241c8f3aa665dec53c20db77baf93\"},{\"ctime\":\"2016-05-28\",\"title\":\"美国队长变节成九头蛇卧底，我们该恭喜漫威吗？\",\"description\":\"虎嗅网\",\"picUrl\":\"http://zxpic.gtimg.com/infonew/0/wechat_pics_-5815039.jpg/640\",\"url\":\"http://mp.weixin.qq.com/s?__biz=MTQzMjE1NjQwMQ==&idx=1&mid=2655533855&sn=9891d09ecccb857c8e0d25bb8a49f50b\"},{\"ctime\":\"2016-05-26\",\"title\":\"美国队长是卧底？！漫威：我们从2014年就在盘算这事~\",\"description\":\"果壳网\",\"picUrl\":\"http://zxpic.gtimg.com/infonew/0/wechat_pics_-5747356.jpg/640\",\"url\":\"http://mp.weixin.qq.com/s?__biz=MTg1MjI3MzY2MQ==&idx=2&mid=2651676872&sn=96a55b6587beba061d95568869ce0c1b\"},{\"ctime\":\"2016-05-26\",\"title\":\"美国队长成大反派？！主演克里斯埃文斯都懵圈了\",\"description\":\"娱乐星天地\",\"picUrl\":\"http://zxpic.gtimg.com/infonew/0/wechat_pics_-5744473.jpg/640\",\"url\":\"http://mp.weixin.qq.com/s?__biz=MjM5Nzg4ODg1Mg==&idx=2&mid=2655476159&sn=547cd1d37a5a35f6550448bc46f1c97e\"},{\"ctime\":\"2016-05-26\",\"title\":\"朋友圈已刷爆：美国队长叛变成九头蛇卧底！\",\"description\":\"冷丫\",\"picUrl\":\"http://zxpic.gtimg.com/infonew/0/wechat_pics_-5739045.jpg/640\",\"url\":\"http://mp.weixin.qq.com/s?__biz=MjM5NzIyODk4MA==&idx=4&mid=2651373945&sn=a7433c5889dd87e95639c91c56c736dc\"},{\"ctime\":\"2016-05-26\",\"title\":\"漫威世界颠覆！美国队长竟是九头蛇特工扮演者懵逼\",\"description\":\"驱动之家\",\"picUrl\":\"http://zxpic.gtimg.com/infonew/0/wechat_pics_-5740040.jpg/640\",\"url\":\"http://mp.weixin.qq.com/s?__biz=MjM5MTAxNjIyMA==&idx=4&mid=2655036994&sn=765ae3b53994301cc6bbc3edf4ca0a0a\"},{\"ctime\":\"2016-05-26\",\"title\":\"美国队长是秘密特工要黑化了？这是噱头还是噱头还是噱头呢？\",\"description\":\"腾讯娱乐\",\"picUrl\":\"http://zxpic.gtimg.com/infonew/0/wechat_pics_-5737531.jpg/640\",\"url\":\"http://mp.weixin.qq.com/s?__biz=MTA5NTIzNDE2MQ==&idx=2&mid=2653336021&sn=34a8bce27f6f1ac187781de242427e25\"},{\"ctime\":\"2016-05-23\",\"title\":\"它们居然把《美国队长》打下来了，这是一群什么鸟？！\",\"description\":\"南都全娱乐\",\"picUrl\":\"http://zxpic.gtimg.com/infonew/0/wechat_pics_-5634962.jpg/640\",\"url\":\"http://mp.weixin.qq.com/s?__biz=MjM5MTE1ODI2MA==&idx=3&mid=2651787917&sn=adea63cee245c19fd1a2c09a5e6380ad\"},{\"ctime\":\"2016-05-23\",\"title\":\"《美国队长3：内战》：对于自我坚信我们可以付出多少\",\"description\":\"三联生活周刊\",\"picUrl\":\"http://zxpic.gtimg.com/infonew/0/wechat_pics_-5628967.jpg/640\",\"url\":\"http://mp.weixin.qq.com/s?__biz=MTc5MTU3NTYyMQ==&idx=1&mid=2650607614&sn=cc7092fe6c5e941d00f6dd6877b7c934\"},{\"ctime\":\"2016-05-23\",\"title\":\"《美国队长》凭什么70年人气不减丨壹读精选\",\"description\":\"壹读\",\"picUrl\":\"http://zxpic.gtimg.com/infonew/0/wechat_pics_-5612566.jpg/640\",\"url\":\"http://mp.weixin.qq.com/s?__biz=OTE4MzAyODYx&idx=2&mid=2652193808&sn=2d8e482cd92a938838d509f22568becf\"}]}";
        Gson gson = new Gson();
        WidgetEntity entity = new WidgetEntity();
        List<WidgetEntity.NewslistBean> fakeNewsList = new ArrayList<>();
        entity.setCode(200);
        entity.setMsg("success");
        for(int i=0;i<10;i++)
        {
            WidgetEntity.NewslistBean newslistBean = new WidgetEntity.NewslistBean();
            newslistBean.setCtime("2016-06-07");
            newslistBean.setDescription("我是测试的,序号为" + i);
            newslistBean.setTitle("测试序号为" + i);
            newslistBean.setPicUrl(testpic);
            newslistBean.setUrl("http://www.baidu.com");
            fakeNewsList.add(newslistBean);
        }
        entity.setNewslist(fakeNewsList);
        //WidgetEntity entity = gson.fromJson(teststr, WidgetEntity.class);
        onLoadWidgetInfoListener.loadSuccess(entity);





//
//        Parameters para = new Parameters();
//        ApiStoreSDK.execute("http://apis.baidu.com/txapi/huabian/newtop/?num=10&page=1&key=您自己的ApiKey",
//                ApiStoreSDK.GET,
//                para,
//                new ApiCallBack() {
//                    @Override
//                    public void onSuccess(int status, String responseString) {
//                        Log.i("sdkdemo", "onSuccess___" + responseString);
//                        Gson gson = new Gson();
//                        WidgetEntity entity = gson.fromJson(responseString, WidgetEntity.class);
//                        onLoadWidgetInfoListener.loadSuccess(entity);
//
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.i("sdkdemo", "onComplete");
//                    }
//
//                    @Override
//                    public void onError(int status, String responseString, Exception e) {
//                        Log.i("sdkdemo", "onError, status: " + status);
//                        Log.i("sdkdemo", "errMsg: " + (e == null ? "" : e.getMessage()));
//                        onLoadWidgetInfoListener.loadFailed("请求失败");
//                    }
//
//                });

    }


}
