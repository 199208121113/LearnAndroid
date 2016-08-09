package com.xdf.learn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xdf.learn.R;
import com.xdf.learn.holder.HomeHolder;

import java.util.List;

/**
 * Created by xdf on 16-8-9.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeHolder>{
    private Context context;
    private List<String> dataList;
    public HomeAdapter(Context context,List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public HomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_layout_home,parent,false);
        return new HomeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeHolder holder, int position) {
        holder.tv.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }
}
