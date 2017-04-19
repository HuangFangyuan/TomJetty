package com.hfy.tomjetty.valve;

import com.hfy.tomjetty.interfaces.*;

/**
 * Created by HuangFangyuan on 2017/4/17.
 */
public class StandradContextValve implements Valve {

    Container container;
    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void invoke(Request request, Response response, ValveContext context) {
        System.out.println("this is basic valve");
    }

    @Override
    public void setContainer(Container container) {
        this.container = container;
    }
}
