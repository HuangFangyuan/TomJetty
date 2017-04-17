package com.hfy.tomjetty.interfaces;

/**
 * Created by HuangFangyuan on 2017/4/16.
 */
public interface Lifecycle {

    void strat();
    void stop();
    void addLifecycleListener(LifecycleListener listener);
    LifecycleListener[] findLifecycleListeners();

}
