package com.xdf.learn.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.xdf.learn.R;
import com.xdf.learn.model.TestListItem;
import com.xdf.learn.model.TestListStatus;
import com.youloft.xcore.base.BaseViewHolder;

/**
 * Created by xdf on 16-8-18.
 */
public class TesListHolder extends BaseViewHolder<TestListItem,TestListStatus> {
    TextView itemTitle;

    public TesListHolder(Context context, View rootView) {
        super(context, rootView);
    }

    @Override
    protected void onInitViews(View view) {
        itemTitle = find(R.id.id_item_test_list_tv);
    }

    @Override
    protected void onBindItem() {
        itemTitle.setText(getItem().getData().getIteName());
    }

    @Override
    protected void onResetViews() {

    }

    @Override
    protected void onRecycleItem() {

    }

    @Override
    protected void onRefreshView() {

    }

    @Override
    protected void onDestory() {

    }
}
