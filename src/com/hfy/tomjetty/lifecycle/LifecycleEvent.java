package com.hfy.tomjetty.lifecycle;

import com.hfy.tomjetty.interfaces.Lifecycle;

/**
 * Created by HuangFangyuan on 2017/4/16.
 */
public class LifecycleEvent<T> {

    public LifecycleEvent(String type, T data, Lifecycle lifecycle) {
        this.type = type;
        this.data = data;
        this.lifecycle = lifecycle;
    }

    String type;
    T data;
    Lifecycle lifecycle;

}
