package com.xdf.learn.base;

import android.app.Application;

import com.zhy.changeskin.SkinManager;

/**
 * Created by xdf on 16-9-12.
 */
public class SupperApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);
    }
}
