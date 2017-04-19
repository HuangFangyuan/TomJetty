package com.hfy.tomjetty.valve;

import com.hfy.tomjetty.interfaces.*;

/**
 * Created by HuangFangyuan on 2017/4/17.
 */
public class UrlLogValve implements Valve {

    Container container;
    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void invoke(Request request, Response response, ValveContext context) {
        context.invokeNext(request,response);
        System.out.println("this is url log valve");
        System.out.println(request.getHeader().getUrl());
    }

    @Override
    public void setContainer(Container container) {
        this.container = container;
    }
}
