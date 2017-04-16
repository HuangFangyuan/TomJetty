package com.hfy.tomjetty.server;

/**
 * Created by HuangFangyuan on 2017/4/16.
 */
public interface Lifecycle {

    LifecycleListener[] listener = null;
    void strat();
    void stop();
    void addLifecycleListener(LifecycleListener listener);
    LifecycleListener findLifecycleListener();

}
