package com.xdf.learn.base;

import android.app.Activity;
import android.os.Bundle;

import com.xdf.learn.util.ViewInjectUtils;

/**
 * base
 * Created by xdf on 16-8-9.
 */
public class BaseActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);
    }
}
