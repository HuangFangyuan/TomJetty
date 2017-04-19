package com.hfy.tomjetty.interfaces;

import com.hfy.tomjetty.lifecycle.LifecycleEvent;

/**
 * Created by HuangFangyuan on 2017/4/16.
 */
public interface LifecycleListener {
    void lifecycleEvent(LifecycleEvent event);
}
