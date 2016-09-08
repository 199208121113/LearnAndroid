package com.xdf.learn.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.xdf.learn.R;
import com.xdf.learn.annotation.ContentView;
import com.xdf.learn.base.BaseActivity;
import com.youloft.xcore.util.LogUtil;

/**
 * 测试Activity声明周期
 * <p onContentChanged， onPostCreate， onPostResume， onConfigurationChanged， onSaveInstanceState， onRestoreInstanceState，/>
 * Created by xdf on 16-9-8.
 */
public class LifeCycleTestActivity extends Activity {
    private static final String tag = LifeCycleTestActivity.class.getSimpleName();

    public static Intent createIntent(Context context){
        return new Intent(context,LifeCycleTestActivity.class);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        //Activity的回调方法，布局改动，即setContentView或者addContentView执行完毕时就调用
        //Activity中的各种findViewById方法都可以放到这个里面
        LogUtil.d(tag,"=====onContentChanged=====");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_life_cycle);
        LogUtil.d(tag,"=====onCreate=====");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(tag,"=====onStart=====");
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //指onCreate方法彻底执行完毕的回调，google官方一般不推荐这两个方法重写
        LogUtil.d(tag,"=====onPostCreate=====");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(tag,"=====onResume=====");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        LogUtil.d(tag,"=====onPostResume=====");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d(tag,"=====onPause=====");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d(tag,"=====onStop=====");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(tag,"=====onDestroy=====");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtil.d(tag,"=====onConfigurationChanged=====");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtil.d(tag,"=====onSaveInstanceState=====");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogUtil.d(tag,"=====onRestoreInstanceState=====");
    }
}
