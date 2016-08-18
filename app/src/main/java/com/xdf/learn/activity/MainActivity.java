package com.xdf.learn.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xdf.learn.R;
import com.xdf.learn.adapter.HomeAdapter;
import com.xdf.learn.annotation.ContentView;
import com.xdf.learn.annotation.InjectView;
import com.xdf.learn.base.BaseActivity;
import com.xdf.learn.view.DividerListItemDecoration;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity{
    @InjectView(R.id.id_main_recycle_view)
    RecyclerView mReCycleView;

    private HomeAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new HomeAdapter();
        mReCycleView.addItemDecoration(new DividerListItemDecoration(this, DividerListItemDecoration.VERTICAL_LIST));
        mReCycleView.setLayoutManager(new LinearLayoutManager(this));
        mReCycleView.setAdapter(adapter);
        adapter.addItem("Android5.0学习系列");
        adapter.addItem("Android5.0学习系列");
        adapter.addItem("Android5.0学习系列");
        adapter.addItem("Android5.0学习系列");
    }
}
