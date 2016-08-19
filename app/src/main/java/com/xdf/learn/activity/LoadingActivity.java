package com.xdf.learn.activity;

import android.os.Bundle;
import android.os.Handler;

import com.xdf.learn.R;
import com.xdf.learn.annotation.ContentView;
import com.xdf.learn.base.BaseActivity;

/**
 * Created by xdf on 16-8-18.
 */
@ContentView(R.layout.act_load_layout)
public class LoadingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(TestListViewActivity.createIntent(LoadingActivity.this));
                finish();
            }
        },3000);
    }
}
