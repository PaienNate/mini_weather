package com.example.myapplication.widget.mvp.view;


import com.example.myapplication.bean.WidgetEntity;

/**
 * @author David  create on 2016/8/11  9:54.
 * @email david.forever.god@gmail.com
 * Learn from yesterday, live for today, hope for tomorrow.
 */

public interface GridInfoView {
    void loadWidgetInfo(WidgetEntity widgetEntity);

    void loadFailed(String s);
}
