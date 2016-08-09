package com.xdf.learn.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xdf.learn.R;

/**
 * Created by xdf on 16-8-9.
 */
public class HomeHolder extends RecyclerView.ViewHolder {
    public TextView tv;
    public HomeHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.item_home_tv);
    }
}
