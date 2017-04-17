package com.hfy.tomjetty.interfaces;

/**
 * Created by HuangFangyuan on 2017/4/17.
 */
public interface Valve {

    String getInfo();
    void invoke(Request request,Response response,ValveContext context);
    void setContainer(Container container);
}
