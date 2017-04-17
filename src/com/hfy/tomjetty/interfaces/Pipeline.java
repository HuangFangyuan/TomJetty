package com.hfy.tomjetty.interfaces;

/**
 * Created by HuangFangyuan on 2017/4/16.
 */
public interface Pipeline {

    void invoke(Request request,Response response);
    void addValve(Valve valve);
    void removeValve(Valve valve);
    void setBasic(Valve valve);
    Valve getBasic();
}
