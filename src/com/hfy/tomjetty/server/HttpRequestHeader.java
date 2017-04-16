package com.hfy.tomjetty.server;

import java.util.HashMap;

/**
 * Created by HuangFangyuan on 2017/4/15.
 */
public class HttpRequestHeader {

    private String method;
    private String url;
    private String protocol;
    private String host;
    private String connection;
    private String cache_control;
    private String upgrade_insecure_requests;
    private String user_agent;
    private String accept;
    private String accept_encoding;
    private String accept_language;

    private HashMap<String,String> map;
    private String text;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getCache_control() {
        return cache_control;
    }

    public void setCache_control(String cache_control) {
        this.cache_control = cache_control;
    }

    public String getUpgrade_insecure_requests() {
        return upgrade_insecure_requests;
    }

    public void setUpgrade_insecure_requests(String upgrade_insecure_requests) {
        this.upgrade_insecure_requests = upgrade_insecure_requests;
    }

    public String getUser_agent() {
        return user_agent;
    }

    public void setUser_agent(String user_agent) {
        this.user_agent = user_agent;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getAccept_encoding() {
        return accept_encoding;
    }

    public void setAccept_encoding(String accept_encoding) {
        this.accept_encoding = accept_encoding;
    }

    public String getAccept_language() {
        return accept_language;
    }

    public void setAccept_language(String accept_language) {
        this.accept_language = accept_language;
    }

    public HashMap<String, String> getMap() {
        return map;
    }

    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "HttpRequestHeader{" +
                "method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", protocol='" + protocol + '\'' +
                ", host='" + host + '\'' +
                ", connection='" + connection + '\'' +
                ", cache_control='" + cache_control + '\'' +
                ", upgrade_insecure_requests='" + upgrade_insecure_requests + '\'' +
                ", user_agent='" + user_agent + '\'' +
                ", accept='" + accept + '\'' +
                ", accept_encoding='" + accept_encoding + '\'' +
                ", accept_language='" + accept_language + '\'' +
                '}';
    }
}
