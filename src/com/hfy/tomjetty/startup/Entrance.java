package com.hfy.tomjetty.startup;

import com.hfy.tomjetty.server.Connector;
import com.hfy.tomjetty.valve.MethodLogValve;
import com.hfy.tomjetty.server.StandradWrapper;
import com.hfy.tomjetty.valve.UrlLogValve;

/**
 * Created by HuangFangyuan on 2017/4/15.
 */
public class Entrance {
    public static void main(String[] args) {
        Connector connector = new Connector();
        StandradWrapper wrapper = new StandradWrapper();
        wrapper.addValve(new MethodLogValve());
        wrapper.addValve(new UrlLogValve());
        wrapper.strat();
        connector.setContainer(wrapper);
        new Thread(connector).start();
    }
}
