package com.xdf.learn;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.xdf.learn.adapter.HomeAdapter;
import com.xdf.learn.base.BaseActivity;
import com.xdf.learn.annotation.ContentView;
import com.xdf.learn.annotation.InjectView;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @InjectView(R.id.id_main_recycle_view)
    RecyclerView mReCycleView;

    private List<String> dataList;
    private HomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataList = new ArrayList<>();
        dataList.add("RecycleView添加Header和Footer");
        adapter = new HomeAdapter(this,dataList);
        mReCycleView.setLayoutManager(new LinearLayoutManager(this));
        mReCycleView.setAdapter(adapter);
    }
}
