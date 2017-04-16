package com.hfy.tomjetty.server;

import java.util.HashMap;

/**
 * Created by HuangFangyuan on 2017/4/15.
 */
public class HttpHeaderParser {

    public HttpRequestHeader parse(String msg){

        HttpRequestHeader head = new HttpRequestHeader();
        String[] lines = msg.split("\n");
        HashMap<String,String> map = new HashMap<>();
        //请求行解析
        String[] requst_line = lines[0].split(" ");
        map.put("Method",requst_line[0]);
        map.put("Url",requst_line[1]);
        map.put("Protocol",requst_line[2].replace('\r',' ').trim());
        //host解析
        String host = lines[1];
        map.put("Host",host.substring(host.indexOf(":")+1).replace('\r',' ').trim());
        //其余首部行解析
        for (int i = 2; i < lines.length -1; i++) {
            String[] result = lines[i].split(":");
            map.put(result[0],result[1].replace('\r', ' ').trim());
        }

        head.setMethod(map.get("Method"));
        head.setUrl(map.get("Url"));
        head.setProtocol(map.get("Protocol"));
        head.setHost(map.get("Host"));
        head.setConnection(map.get("Connection"));
        head.setCache_control(map.get("Cache-Control"));
        head.setUpgrade_insecure_requests(map.get("Upgrade-Insecure-Requests"));
        head.setUser_agent(map.get("User-Agent"));
        head.setAccept(map.get("Accept"));
        head.setAccept_encoding(map.get("Accept-Encoding"));
        head.setAccept_language(map.get("Accept-Language"));
        head.setText(msg);
        head.setMap(map);

        return head;
    }
}
