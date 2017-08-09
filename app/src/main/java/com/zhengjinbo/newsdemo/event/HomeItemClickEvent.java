package com.zhengjinbo.newsdemo.event;

/**
 * Created by zhengjinbo.
 */

public class HomeItemClickEvent {
    private long mId;

    public HomeItemClickEvent(long id) {
        this.mId = id;
    }

    public long getId() {
        return mId;
    }
}
