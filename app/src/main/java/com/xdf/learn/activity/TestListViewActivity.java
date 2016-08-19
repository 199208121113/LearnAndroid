package com.xdf.learn.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import com.xdf.learn.R;
import com.xdf.learn.adapter.TestListAdapter;
import com.xdf.learn.annotation.ContentView;
import com.xdf.learn.annotation.InjectView;
import com.xdf.learn.base.BaseActivity;
import com.xdf.learn.model.TestListItem;

/**
 * xdf
 * Created by xdf on 16-8-18.
 */
@ContentView(R.layout.act_test_list)
public class TestListViewActivity extends BaseActivity implements View.OnClickListener{
    @InjectView(R.id.id_act_test_list)
    ListView mListView;

    public static Intent createIntent(Context ctx){
        return new Intent(ctx,TestListViewActivity.class);
    }

    TestListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addHeaderView();
        adapter = new TestListAdapter(this);
        mListView.setAdapter(adapter);
        loadTestData();
    }

    @Override
    public void onClick(View view) {
        if(view == userIcon){

        }
    }

    ImageView userIcon;
    private void addHeaderView(){
        View headerView = LayoutInflater.from(this).inflate(R.layout.header_test_list,null);
        userIcon = (ImageView)headerView.findViewById(R.id.header_test_list_icon);
        userIcon.setOnClickListener(this);
        mListView.addHeaderView(headerView);
    }

    private void loadTestData(){
        for (int i = 0; i < 10; i++){
            adapter.addItem(new TestListItem("测试Item"+(i+1)),null);
        }
        adapter.notifyDataSetChanged();
    }
}
