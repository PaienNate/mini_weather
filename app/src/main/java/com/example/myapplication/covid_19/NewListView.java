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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
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
