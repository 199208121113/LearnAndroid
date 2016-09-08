package com.youloft.xcore.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 共用的Adapter
 * <p/>
 * Created by xdf on 16-9-8.
 */
public abstract class SimpleBaseAdapter<T> extends BaseAdapter {
    protected Context context;
    protected List<T> dataList;

    public SimpleBaseAdapter(Context context, List<T> dataList) {
        this.context = context;
        this.dataList = dataList == null ? new ArrayList<T>() : dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public T getItem(int position) {
        if (position >= dataList.size())
            return null;
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 该方法需要子类实现，需要返回item布局的resource id
     *
     * @return
     */
    public abstract int getItemResource();

    /**
     * 使用该getItemView方法替换原来的getView方法，需要子类实现
     *
     * @param position
     * @param convertView
     * @param holder
     * @return
     */
    public abstract View getItemView(int position, View convertView, SimpleBaseViewHolder holder);

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        SimpleBaseViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, getItemResource(), null);
            holder = new SimpleBaseViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (SimpleBaseViewHolder) convertView.getTag();
        }

        return getItemView(position, convertView, holder);
    }

    public void addAll(List<T> elem) {
        dataList.addAll(elem);
        notifyDataSetChanged();
    }

    public void remove(T elem) {
        dataList.remove(elem);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        dataList.remove(index);
        notifyDataSetChanged();
    }

    public void replaceAll(List<T> elem) {
        dataList.clear();
        dataList.addAll(elem);
        notifyDataSetChanged();
    }

    public void addItem(T elem){
        dataList.add(elem);
        notifyDataSetChanged();
    }
}
