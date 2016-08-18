package com.youloft.xcore.inter;

import android.view.View;

import com.youloft.xcore.model.AdapterItem;

/**
 * Created by xdf on 16-8-17.
 */
public interface OnAdapterItemStateChangeListener<DATA,STATE> {
    void onStateChanged(AdapterItem<DATA,STATE> item, View v,int... posIndex);
}
