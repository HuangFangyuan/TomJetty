package com.hfy.tomjetty.startup;

import com.hfy.tomjetty.server.Connector;
import com.hfy.tomjetty.server.SimpleValve;
import com.hfy.tomjetty.server.SimpleWrapper;

import java.sql.Wrapper;

/**
 * Created by HuangFangyuan on 2017/4/15.
 */
public class Entrance {
    public static void main(String[] args) {
        Connector connector = new Connector();
        SimpleWrapper wrapper = new SimpleWrapper();
        wrapper.setBasic(new SimpleValve());
        connector.setWrapper(wrapper);
        new Thread(connector).start();
    }
}
