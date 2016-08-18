package com.xdf.learn.adapter;

import android.content.Context;
import android.view.View;

import com.xdf.learn.R;
import com.xdf.learn.holder.TesListHolder;
import com.xdf.learn.model.TestListItem;
import com.xdf.learn.model.TestListStatus;
import com.youloft.xcore.base.BaseListViewAdapter;
import com.youloft.xcore.base.BaseViewHolder;

/**
 * Created by xdf on 16-8-18.
 */
public class TestListAdapter extends BaseListViewAdapter<TestListItem,TestListStatus> {

    public TestListAdapter(Context act) {
        super(act);
    }

    @Override
    protected void onInitViewType() {
        addViewType(TestListItem.class, R.layout.item_test_list);
    }

    @Override
    protected BaseViewHolder<TestListItem, TestListStatus> onCreateViewHolder(View view, Context act) {
        return new TesListHolder(act,view);
    }

    @Override
    protected void onDestory() {

    }
}
