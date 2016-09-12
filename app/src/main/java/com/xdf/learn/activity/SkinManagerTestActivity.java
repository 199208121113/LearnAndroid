package com.xdf.learn.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xdf.learn.R;
import com.xdf.learn.annotation.ContentView;
import com.xdf.learn.annotation.InjectView;
import com.xdf.learn.base.SupperActivity;
import com.zhy.changeskin.SkinManager;

/**
 * 换肤测试Activity
 * Created by xdf on 16-9-12.
 */
@ContentView(R.layout.act_skin_test)
public class SkinManagerTestActivity extends SupperActivity implements View.OnClickListener {


    @InjectView(R.id.id_tv_change_skin)
    TextView testTv;

    @InjectView(R.id.skin_red)
    Button btnRed;

    @InjectView(R.id.skin_green)
    Button btnGreen;

    @InjectView(R.id.skin_blue)
    Button btnBlue;

    @InjectView(R.id.skin_white)
    Button btnWhite;

    public static Intent createIntent(Context context) {
        return new Intent(context, SkinManagerTestActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnRed.setOnClickListener(this);
        btnGreen.setOnClickListener(this);
        btnBlue.setOnClickListener(this);
        btnWhite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnRed) {
            SkinManager.getInstance().changeSkin("red");
        } else if (v == btnGreen) {
            SkinManager.getInstance().changeSkin("green");
        } else if (v == btnBlue) {
            SkinManager.getInstance().changeSkin("blue");
        } else if (v == btnWhite) {
            SkinManager.getInstance().changeSkin("white");
        }
    }
}
