package com.xdf.learn.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.xdf.learn.R;
import com.youloft.xcore.base.SimpleBaseAdapter;
import com.youloft.xcore.base.SimpleBaseViewHolder;

import java.util.List;

/**
 * Created by xdf on 16-9-8.
 */
public class SimpleAdapterTestAdapter extends SimpleBaseAdapter<String> {

    public SimpleAdapterTestAdapter(Context context, List<String> dataList) {
        super(context, dataList);
    }

    @Override
    public int getItemResource() {
        return R.layout.item_test_list;
    }

    @Override
    public View getItemView(int position, View convertView, SimpleBaseViewHolder holder) {
        TextView tv = holder.getView(R.id.id_item_test_list_tv);
        tv.setText(getItem(position));
        return convertView;
    }
}
