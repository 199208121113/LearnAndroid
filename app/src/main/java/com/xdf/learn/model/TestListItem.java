package com.xdf.learn.model;

import com.youloft.xcore.inter.AdapterEntity;

/**
 * Created by xdf on 16-8-18.
 */
public class TestListItem implements AdapterEntity {

    private int itemId;
    private String iteName;

    public TestListItem() {
    }

    public TestListItem(int itemId, String iteName) {
        this.itemId = itemId;
        this.iteName = iteName;
    }

    public String getIteName() {
        return iteName;
    }

    public void setIteName(String iteName) {
        this.iteName = iteName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
