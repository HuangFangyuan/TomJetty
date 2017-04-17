package com.hfy.tomjetty.server;

import com.hfy.tomjetty.interfaces.Request;

/**
 * Created by HuangFangyuan on 2017/4/16.
 */
public class HttpServletRequest implements Request {

    HttpRequestHeader header;
    @Override
    public void setHeader(HttpRequestHeader header) {
        this.header = header;
    }

    @Override
    public HttpRequestHeader getHeader() {
        return header;
    }
}
