package com.xdf.learn.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xdf.learn.util.ViewInjectUtils;

/**
 * base
 * Created by xdf on 16-8-9.
 */
public class BaseActivity extends AppCompatActivity{
    protected String tag = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);
    }
}
