package com.youloft.xcore.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.youloft.xcore.inter.AdapterEntity;
import com.youloft.xcore.inter.IViewHolder;
import com.youloft.xcore.inter.OnAdapterItemStateChangeListener;
import com.youloft.xcore.model.AdapterItem;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xdf on 16-8-17.
 */
public abstract class BaseListViewAdapter<T extends AdapterEntity, STATE> extends BaseAdapter {
    protected final String TAG = this.getClass().getSimpleName();

    private final ArrayList<AdapterItem<T, STATE>> mData = new ArrayList<>();
    private HashMap<String, Integer> mViewTypeMap = new HashMap<>();
    private SparseArray<IViewHolder<T, STATE>> mHolderList = new SparseArray<>();
    private Context context = null;
    private LayoutInflater mInflater = null;

    //ConcurrentHashMap 引入了一个“分段锁”的概念，具体可以理解为把一个大的Map拆分成N个小的HashTable
    // 根据key.hashCode()来决定把key放到哪个HashTable中 对比与HashMap而言是线程安全的
    private final ConcurrentHashMap<String, IViewHolder<T, STATE>> viewMap = new ConcurrentHashMap<>();

    protected abstract void onInitViewType();

    protected abstract BaseViewHolder<T, STATE> onCreateViewHolder(View view, Context act);

    protected BaseViewHolder<T, STATE> onCreateViewHolder(View view, Context act, Type dataType, int posIndex) {
        return null;
    }

    protected abstract void onDestory();

    public BaseListViewAdapter(Context act) {
        super();
        this.context = act;
        mInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        onInitViewType();
    }

    protected LayoutInflater getLayoutInflater() {
        if (mInflater == null) {
            mInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        return mInflater;
    }

    public void addViewType(Class<? extends AdapterEntity> c, int layout) {
        String key = c.getName();
        mViewTypeMap.put(key, layout);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public AdapterItem<T, STATE> getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        BaseViewHolder<T, STATE> holder;
        AdapterItem<T, STATE> item = getItem(position);
        Type dataType = item.getData().getClass();
        if (convertView == null) {
            convertView = inflateView(position, null, viewGroup);
            if (convertView == null) return null;
            holder = createViewHolder(convertView, position, dataType);
            viewMap.put(holder.toString(), holder);
        } else {
            holder = (BaseViewHolder<T, STATE>) convertView.getTag();
            holder.setPosIndex(position);
            holder.onLayout();
        }
        convertView.setTag(holder);
        holder.setItem(item);
        mHolderList.put(position, holder);
        return convertView;
    }

    private AdapterItem<T,STATE> convertDataToAdapterItem(T data,STATE state){
        if (data == null) return null;
        return new AdapterItem<>(data,state);
    }

    public AdapterItem<T,STATE> addItem(T data,STATE state){
        return addItem(mData.size(),data,state);
    }

    public AdapterItem<T,STATE> addItem(int index, T data, STATE state){
        AdapterItem<T,STATE> adapterItem = convertDataToAdapterItem(data,state);
        if(adapterItem != null)
            mData.add(index,adapterItem);

        return adapterItem;
    }

    private AdapterItem<T,STATE> addItem(AdapterItem<T,STATE> ai){
        mData.add(ai);
        return ai;
    }

    public AdapterItem<T, STATE> addItem(T data, STATE state, OnAdapterItemStateChangeListener<T, STATE> listener) {
        AdapterItem<T, STATE> item = addItem(data, state);
        if (item != null)
            item.setOnAdapterItemStateChangeListener(listener);
        return item;
    }

    public List<AdapterItem<T, STATE>> addItems(List<T> datas, List<STATE> states){
        if(datas == null || datas.size() == 0){
            return null;
        }
        List<AdapterItem<T, STATE>> items = new ArrayList<>();
        for (int i = 0; i < datas.size() ; i++){
            STATE st = states != null && states.size() > i ? states.get(i) : null;
            AdapterItem<T, STATE> item = addItem(datas.get(i),st);
            if(item != null){
                items.add(item);
            }
        }
        return items;
    }

    public void delItem(AdapterItem<T, STATE> item) {
        if (item == null || mData == null || mData.size() == 0)
            return;
        mData.remove(item);
    }

    public AdapterItem<T, STATE> delItem(int position) {
        if(mData == null)
            return null;
        if (position >= mData.size())
            return null;
        AdapterItem<T, STATE> item = mData.remove(position);
        return item;
    }

    public void clearItems() {
        if(mData != null) {
            mData.clear();
        }
    }

    private BaseViewHolder<T, STATE> createViewHolder(View view, int posIndex, Type dataType) {
        BaseViewHolder<T, STATE> holder = onCreateViewHolder(view, this.context, dataType, posIndex);
        if (holder == null) {
            holder = onCreateViewHolder(view, this.context);
        }

        holder.setPosIndex(posIndex);
        holder.setDataType(dataType);
        holder.initViews();
        return holder;
    }

    private View inflateView(int position, View convertView, ViewGroup parent) {
        int typeCount = getViewTypeCount();
        if (typeCount == 1) {
            int resource = getResourceId(position);
            View v = convertView;
            if (convertView == null) {
                v = mInflater.inflate(resource, parent, false);
            }
            return v;
        } else if (typeCount > 1) {
            int resource = getResourceIdByItemViewType(position);
            return mInflater.inflate(resource, parent, false);
        }
        return null;
    }

    protected Integer getResourceIdByItemViewType(int position) {
        return getResourceId(position);
    }

    protected Integer getResourceId(int position) {
        AdapterItem<T, STATE> item = getItem(position);
        return getResourceId(item.getData());
    }

    protected int getResourceId(T data) {
        final String className = data.getClass().getName();
        if (mViewTypeMap.containsKey(className)) {
            return mViewTypeMap.get(className);
        }
        String parentClassName = data.getClass().getSuperclass().getName();
        if (mViewTypeMap.containsKey(parentClassName)) {
            return mViewTypeMap.get(parentClassName);
        }
        throw new RuntimeException("cant't find layout for className:" + className);
    }

}
