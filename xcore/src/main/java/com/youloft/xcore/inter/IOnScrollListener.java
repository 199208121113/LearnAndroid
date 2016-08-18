package com.youloft.xcore.inter;

import android.view.ViewGroup;

/**
 * Created by xdf on 16-8-17.
 */
public interface IOnScrollListener {
    void onScrollStateChanged(ViewGroup vg,int scrollState);
    void onScroll(ViewGroup vg,int firstVisibleItem, int visibleItemCount, int totalItemCount);
}
