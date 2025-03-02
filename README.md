# 项目

迷你天气系统 - 魔改版（？）

帮上忙的话可以过来点个star什么的，不然这项目我都不知道多少人拿走了……

## 介绍

该项目基于https://www.bilibili.com/video/av74865043/ 视频的项目，下方评论区的“换用腾讯天气API”项目二次开发。

基于的项目地址：https://github.com/I-am-panda/Application

该项目用于过大三的安卓课设，且已经过了课设。

警告：请一定确定您的项目老师和答辩老师，是否“默认开发必须有后端”，因为该项目无后端，任务量不足以支持三人，我们被答辩老师骂了个狗血淋头！

本人由于是现学现卖，这项目只能说能跑，性能优化什么的，肯定没有（）

该项目混入了一部分retrofit + rxjava的内容，都可以用xutils替换，无所谓了。

## 功能及修正

### 功能

在原本的基础上，功能添加：

1. 可以切换自己想选择的任意壁纸，需要提前放到图库，如图：

   ![image-20220626102742106](img/image-20220626102742106.png)

2. 添加了搜索城市IP反查的功能：

![image-20220626102952586](img/image-20220626102952586.png)

3. 搜索框支持模糊搜索城市：

   ![image-20220626103015056](img/image-20220626103015056.png)

4. 天气小组件，以及根据时间切换右侧天气图标（早上和晚上）：

   ![image-20220626103138820](img/image-20220626103138820.png)

5. 当地抗疫新闻速览（数据来源于腾讯接口）：

   ![image-20220626103212954](img/image-20220626103212954.png)

   ![image-20220626103222294](img/image-20220626103222294.png)

### 修正

   1. 修正了写死城市的相关数据，现在会通过免费API获取，免费API地址：https://www.mxnzp.com/doc/detail?id=8
   1. 写了许许多多的注释，方便后来人直接拿走。
   1. 由于我们项目是多人的（注：因此被骂的很惨），所以移动了一部分代码位置，让它看起来更像多人项目。代码名称基本无变化。

## 使用方法

1. 把项目下载下来，反正按照您高兴的方式搞下来就是了。

2. 到https://www.mxnzp.com/doc/detail?id=8 注册生成一个appid 和 appsecret，然后找到代码中RetrofitHelper.java如下位置：

   ```java
       //用于免费接口的秘钥
       public static String app_id = "*";
       public static String app_secret = "*";
   ```

替换成自己的秘钥即可。

3. 编译，运行。

## 你需要知道的BUG/问题

1. 由于城市接口是免费API不是腾讯的，所以这玩意有点怪，有些城市是请求不到的。一般来说，你只要不点什么“海北藏族自治州”，普通的市都是没有问题的（注：没有说看不起什么偏远地方的意思，只是说因为腾讯天气API也是我抓包的，我也不知道传什么……“
2. 我们使用的虚拟机版本为API22 安卓5.1，如果您无法复现，可以考虑降低虚拟机版本。另：该软件的打包在安卓12上通过，但是会有一些无伤大雅的显示小BUG。
3. 由于为了显示成多人开发的，在某些代码里，混入了retrofit + rxjava的实现代码，retrofit和xutils其实用法差不太多就是配置起来麻烦，虽然我已经写了注释，如果您没有信心看懂，可以考虑自行换成xutils的实现。由于我是跟着这个库学习的，所以您也可以自行尝试分析这个库的代码进行学习：https://github.com/githubhaohao/MVVMRxJavaRetrofitSample 原本的库的API接口已经无了，所以也可以看我翻新的库：https://github.com/PaienNate/MVVMRxJavaRetrofitSample_Fix  同样写了一堆注释（我学习的时候习惯分析一句代码写一句注释，导致注释可能不正确但是贼多）
4. 请将您的虚拟机的分辨率稍微调大一些，否则可能会导致显示异常。
5. 由于我并没有看B站的相关视频（因为我是被组员甩给了一个项目开始改的），如果有什么写的不好的地方，一切以B站老师讲的为准。同样的，因为我已经结束了课设，该库不做其他维护，仅仅是分享一下，方便学习和交流。
6. 邯郸那个搬了新校区的，一本的学生，谨慎使用该课设。
