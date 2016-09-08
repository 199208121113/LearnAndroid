package com.xdf.learn.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.xdf.learn.R;
import com.xdf.learn.adapter.SimpleAdapterTestAdapter;
import com.xdf.learn.annotation.ContentView;
import com.xdf.learn.annotation.InjectView;
import com.xdf.learn.base.BaseActivity;

import java.util.List;

/**
 * 另类Adapter封装测试
 *
 * Created by xdf on 16-9-8.
 */
@ContentView(R.layout.act_simple_adapter_layout)
public class SimpleBaseAdapterTestActivity extends BaseActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, SimpleBaseAdapterTestActivity.class);
    }

    @InjectView(R.id.act_simple_adapter_list)
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleAdapterTestAdapter adapterTestAdapter = new SimpleAdapterTestAdapter(this,null);
        mListView.setAdapter(adapterTestAdapter);
        adapterTestAdapter.addItem("测试数据1");
        adapterTestAdapter.addItem("测试数据2");
        adapterTestAdapter.addItem("测试数据3");
        adapterTestAdapter.addItem("测试数据4");
        adapterTestAdapter.addItem("测试数据5");
        adapterTestAdapter.addItem("测试数据6");
        adapterTestAdapter.addItem("测试数据7");
        adapterTestAdapter.addItem("测试数据8");
    }
}
