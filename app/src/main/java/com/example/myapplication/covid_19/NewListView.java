package com.example.myapplication.covid_19;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
/*
1. 重写listView类方法
2．添加该方法
@Override
protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
super.onMeasure(widthMeasureSpec, expandSpec);
}

3. 将listView的onfoucase=”false(估计就是下面的重写法)
4. 外围将LinearLayout(或者RelativeLayout)包裹所有的空件，再将scorllView包裹该LinearLayout布局控件
5. 完成操作，测试
 */
public class NewListView extends ListView {
    public NewListView(Context context) {
        super(context);
    }

    public NewListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NewListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //onMeasure()方法的作用就是测量View需要多大的空间，就是宽和高，
    //Integer.MAX_VALUE 表示 int 数据类型的最大取值数
    //OnMeasure传入的两个int 值，分别代表宽和高需要的多大空间
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //这里的操作就是，生成一个高最大的值
        //根据开发者文档，MeasureSpec是一个int型（32位，其实多少位无所谓）
        //前两位表示的是“模式”，后面的那30位表示组件大小的值

        //1.精确模式（MeasureSpec.EXACTLY）
        //在这种模式下，尺寸的值是多少，那么这个组件的长或宽就是多少。
        //2.最大模式（MeasureSpec.AT_MOST）
        //这个也就是父组件，能够给出的最大的空间，当前组件的长或宽最大只能为这么大，当然也可以比这个小。
        //3.未指定模式（MeasureSpec.UNSPECIFIED）
        //这个就是说，当前组件，可以随便用空间，不受限制

        //MeasureSpec.makeMeasureSpec(组件大小,模式);这个是由我们给出的组件尺寸大小和模式
        // 生成一个包含这两个信息的int变量。
        //这位博主的代码的想法是：让其永远大小都是最大模式的最大值
        //那么一个Int最大值是Integer.MAX_VALUE,>>2代表移位，也就是说，整数二进制最大值是32个1，它得到的就是30个1
        //配合上前两位表示的是模式，就完成了这个代码想法

        //根据stackOverFlow的说法：
        //这种方式创建了一个不可滚动的自定义 ListView
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
    @Override
    public boolean isFocused()
    {
        //按照一个不存在的博客的说法，使用这种方式（第二步）
        return false;
    }


}
