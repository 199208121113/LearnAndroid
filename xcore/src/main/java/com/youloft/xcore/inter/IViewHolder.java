package com.youloft.xcore.inter;

import android.view.View;
import android.view.ViewGroup;

import com.youloft.xcore.model.AdapterItem;

/**
 * Created by xdf on 16-8-17.
 */
public interface IViewHolder<DATA,STATE> extends IOnScrollListener{

    void setItem(AdapterItem<DATA,STATE> item);

    AdapterItem<DATA,STATE> getItem();

    void bindItem();

    void refreshView();

    View getRootView();

    void initViews();

    void resetViews();

    void recycleItem();

    void destory();

}
