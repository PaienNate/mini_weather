package com.example.myapplication.widget.mvp.presenter;


import com.example.myapplication.bean.WidgetEntity;
import com.example.myapplication.widget.mvp.model.GridInfoModel;
import com.example.myapplication.widget.mvp.model.GridInfoModelImpl;
import com.example.myapplication.widget.mvp.view.GridInfoView;

/**
 * @author David  create on 2016/8/11  10:00.
 * @email david.forever.god@gmail.com
 * Learn from yesterday, live for today, hope for tomorrow.
 */
public class GridInfoPresenter implements GridInfoModel.OnLoadWidgetInfoListener {
    private GridInfoView gridInfoView;
    private GridInfoModel gridInfoModel;

    public GridInfoPresenter(GridInfoView gridInfoView) {
        this.gridInfoView = gridInfoView;
        gridInfoModel = new GridInfoModelImpl();
    }

    public void loadWidgetInfo() {
        gridInfoModel.loadWidgetInfo(this);
    }

    @Override
    public void loadSuccess(WidgetEntity infoList) {
        gridInfoView.loadWidgetInfo(infoList);
    }

    @Override
    public void loadFailed(String s) {
        gridInfoView.loadFailed(s);
    }
}
