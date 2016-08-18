package com.xdf.learn.view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by xdf on 16-8-16.
 */
public class BaseRecycleViewAdapter<VH extends BaseRecycleViewHolder>  extends RecyclerView.Adapter<VH>{

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
