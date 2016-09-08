package com.youloft.xcore.base;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by xdf on 16-9-8.
 */
public class SimpleBaseViewHolder{
    private SparseArray<View> views = new SparseArray<>();
    private View convertView;

    public SimpleBaseViewHolder(View convertView) {
        this.convertView = convertView;
    }

    public <T extends View> T getView(int resId){
        View v = views.get(resId);
        if (v == null) {
            v = convertView.findViewById(resId);
            views.put(resId,v);
        }
        return (T)v;
    }
}
