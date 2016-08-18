package com.youloft.xcore.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import com.youloft.xcore.inter.IViewHolder;
import com.youloft.xcore.model.AdapterItem;

import java.lang.reflect.Type;

/**
 * Created by xdf on 16-8-17.
 */
public abstract class BaseViewHolder<DATA,STATE> implements IViewHolder<DATA,STATE> {
    protected final String TAG = this.getClass().getSimpleName();

    protected AdapterItem<DATA,STATE> item = null;
    private View rootView = null;
    private final Context context;

    public BaseViewHolder(Context context, View rootView) {
        super();
        this.context = context;
        this.rootView = rootView;
    }

    protected abstract void onInitViews(View view);

    protected abstract void onBindItem();

    protected abstract void onResetViews();

    protected abstract void onRecycleItem();

    protected abstract void onRefreshView();

    protected abstract void onDestory();

    @Override
    public void setItem(AdapterItem<DATA, STATE> item) {
        if(item == null){
            return;
        }

        boolean isChanged = isChangedForCurrentEntity(item);
        if(!isChanged){
            refreshView();
            return;
        }

        if(this.item != null){
            resetViews();
            recycleItem();
        }

        this.item = item;
        bindItem();
    }

    protected boolean isChangedForCurrentEntity(AdapterItem<DATA,STATE> newItem){
        return this.item != newItem;
    }

    @Override
    public AdapterItem<DATA, STATE> getItem() {
        return this.item;
    }

    @Override
    public void bindItem() { onBindItem();}

    @Override
    public void refreshView() { onRefreshView();}

    @Override
    public View getRootView() {
        return this.rootView;
    }

    @Override
    public void initViews() {
        onInitViews(getRootView());
    }

    @Override
    public void resetViews() {
        onResetViews();
    }

    @Override
    public void recycleItem() {
        onRecycleItem();
    }

    @Override
    public void destory() {
        onDestory();
    }

    @SuppressWarnings("unchecked")
    protected final <V extends View> V find(int id){
        return (V)this.rootView.findViewById(id);
    }

    public final Context getMyContext(){
        return context;
    }

    protected int posIndex = -1;

    public int getPosIndex() {
        return posIndex;
    }

    public void setPosIndex(int posIndex) {
        this.posIndex = posIndex;
    }

    protected int posGroupIndex = -1;

    public int getPosGroupIndex() {
        return posGroupIndex;
    }

    public void setPosGroupIndex(int posGroupIndex) {
        this.posGroupIndex = posGroupIndex;
    }


    protected int scrollState = AbsListView.OnScrollListener.SCROLL_STATE_IDLE;
    protected int firstVisibleItem = 0;
    protected int visibleItemCount = 0;
    protected int totalItemCount = 0;

    @Override
    public void onScrollStateChanged(ViewGroup vg, int scrollState) {
        this.scrollState = scrollState;
    }

    @Override
    public void onScroll(ViewGroup vg, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.firstVisibleItem = firstVisibleItem;
        this.visibleItemCount = visibleItemCount;
        this.totalItemCount = totalItemCount;
    }

    protected int getHeaderCount(){return 1;}

    public void onLayout(){

    }

    private Type dataType;

    public Type getDataType() {
        return dataType;
    }

    public void setDataType(Type dataType) {
        this.dataType = dataType;
    }
}
