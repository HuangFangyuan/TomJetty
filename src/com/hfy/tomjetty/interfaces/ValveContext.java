package com.hfy.tomjetty.interfaces;

/**
 * Created by HuangFangyuan on 2017/4/17.
 */
public interface ValveContext {
    void invokeNext(Request request, Response response);
}