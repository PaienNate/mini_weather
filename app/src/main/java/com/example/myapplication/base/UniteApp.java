package com.example.myapplication.base;

import android.app.Application;
import com.example.myapplication.db.DBManager;
import org.xutils.x;

public class UniteApp extends Application {

    @Override
    public void onCreate() {
        //创建时，先初始化xutils和DBManager,以后就可以用了
        super.onCreate();
        x.Ext.init(this);
        DBManager.initDB(this);
    }
}
