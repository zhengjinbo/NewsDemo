package com.zhengjinbo.newsdemo.event;

/**
 * Created by zhengjinbo on 17/7/23.
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
