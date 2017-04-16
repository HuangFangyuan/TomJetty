package com.hfy.tomjetty.server;

import com.hfy.tomjetty.client.Client;

/**
 * Created by HuangFangyuan on 2017/4/15.
 */
public class Entrance {

    public static void main(String[] args) {
        new Thread(new Connector()).start();
    }
}
