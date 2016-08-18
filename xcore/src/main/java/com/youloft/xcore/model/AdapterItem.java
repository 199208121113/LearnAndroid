package com.youloft.xcore.model;

import android.view.View;

import com.youloft.xcore.inter.OnAdapterItemStateChangeListener;

import java.io.Serializable;

/**
 * Created by xdf on 16-8-17.
 */
public class AdapterItem<DATA,STATE> implements Serializable {

    private static final long serialVersionUID = 1L;

    private DATA data;
    private STATE state;
    private OnAdapterItemStateChangeListener<DATA,STATE> onAdapterItemStateChangeListener = null;

    public AdapterItem(DATA data, STATE state) {
        if (data == null){throw new IllegalArgumentException("data is null");}
        this.data = data;
        this.state = state;
    }

    public DATA getData() {
        return data;
    }

    public STATE getState() {
        return state;
    }

    public OnAdapterItemStateChangeListener<DATA, STATE> getOnAdapterItemStateChangeListener() {
        return onAdapterItemStateChangeListener;
    }

    public void notifyStateChanged(View v, int... posIndex){
        OnAdapterItemStateChangeListener<DATA,STATE> listener = getOnAdapterItemStateChangeListener();
        if(listener != null)
            listener.onStateChanged(this,v,posIndex);
    }

    public void setOnAdapterItemStateChangeListener (OnAdapterItemStateChangeListener<DATA,STATE> onAdapterItemStateChangeListener){
        this.onAdapterItemStateChangeListener = onAdapterItemStateChangeListener;
    }
}
