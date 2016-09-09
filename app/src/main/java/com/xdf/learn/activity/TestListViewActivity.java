package com.xdf.learn.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.xdf.learn.R;
import com.xdf.learn.adapter.TestListAdapter;
import com.xdf.learn.annotation.ContentView;
import com.xdf.learn.annotation.InjectView;
import com.xdf.learn.base.BaseActivity;
import com.xdf.learn.http.DemoService;
import com.xdf.learn.model.TestListItem;
import com.youloft.xcore.util.LogUtil;

import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * xdf
 * Created by xdf on 16-8-18.
 */
@ContentView(R.layout.act_test_list)
public class TestListViewActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final String TAG = TestListViewActivity.class.getSimpleName();
    @InjectView(R.id.id_act_test_list)
    ListView mListView;

    public static Intent createIntent(Context ctx) {
        return new Intent(ctx, TestListViewActivity.class);
    }

    TestListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        addHeaderView();
        adapter = new TestListAdapter(this);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
        loadTestData();
    }

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(100);
    //activity生命周期
    private static final int ACTIVITY_LIFE_CYCLE = ATOMIC_INTEGER.getAndIncrement();
    //另类Adapter封装 相比而言更加简洁
    private static final int BASE_ADAPTER = ATOMIC_INTEGER.getAndIncrement();
    //View的事件机制研究
    private static final int VIEW_EVENT = ATOMIC_INTEGER.getAndIncrement();
    //ViewGroup的事件分发机制研究
    private static final int VIEW_GROUP_EVENT = ATOMIC_INTEGER.getAndIncrement();

    private void loadTestData() {
        adapter.addItem(new TestListItem(ACTIVITY_LIFE_CYCLE, "Activity生命周期"), null);
        adapter.addItem(new TestListItem(BASE_ADAPTER, "另类Adapter封装测试"), null);
        adapter.addItem(new TestListItem(VIEW_EVENT, "View事件机制研究"), null);
        adapter.addItem(new TestListItem(VIEW_GROUP_EVENT, "ViewGroup事件机制研究"), null);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        LogUtil.e(tag, adapter.getCount() + ":" + position);
        if (adapter == null || adapter.getCount() <= 0) {
            return;
        }
        TestListItem itemData = adapter.getItem(position - mListView.getHeaderViewsCount()).getData();
        if (itemData == null) {
            return;
        }
        int itemId = itemData.getItemId();
        if (itemId == ACTIVITY_LIFE_CYCLE) {
            startActivity(LifeCycleTestActivity.createIntent(this));
        } else if (itemId == BASE_ADAPTER) {
            startActivity(SimpleBaseAdapterTestActivity.createIntent(this));
        } else if (itemId == VIEW_EVENT){
            startActivity(ViewEventTestActivity.createIntent(this));
        } else if (itemId == VIEW_GROUP_EVENT) {
            startActivity(ViewGroupEventTestActivity.createIntent(this));
        }
    }

    @Override
    public void onClick(View view) {
        if (view == userIcon) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://config.ireadercity.com/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
            DemoService demoService = retrofit.create(DemoService.class);
            Call<String> call = demoService.testHttpGet("sxyj_android");
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
//                    Log.d(TAG,"onResponse = "+response.body().status);
                    Log.d(TAG, "onResponse = " + response.body().toString());
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d(TAG, "onFailure = " + t.getMessage());
                }
            });
        }
    }

    ImageView userIcon;

    private void addHeaderView() {
        View headerView = LayoutInflater.from(this).inflate(R.layout.header_test_list, null);
        userIcon = (ImageView) headerView.findViewById(R.id.header_test_list_icon);
        userIcon.setOnClickListener(this);
        mListView.addHeaderView(headerView);
    }

}
