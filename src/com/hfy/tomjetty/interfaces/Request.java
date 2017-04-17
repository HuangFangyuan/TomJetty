package com.hfy.tomjetty.interfaces;

import com.hfy.tomjetty.server.HttpRequestHeader;

/**
 * Created by HuangFangyuan on 2017/4/16.
 */
public interface Request {

    void setHeader(HttpRequestHeader header);
    HttpRequestHeader getHeader();
}
