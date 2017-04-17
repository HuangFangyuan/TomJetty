package com.hfy.tomjetty.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by HuangFangyuan on 2017/4/15.
 */
public class Processor {
    //保存连接器
    private Connector connector;

    Processor(Connector connector){
        this.connector = connector;
    }

    //处理传进来的信道
    public HttpRequestHeader process(SocketChannel socketChannel){
        HttpRequestHeader header = null;
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            StringBuilder sb = new StringBuilder();
            int count;
            while ((count = socketChannel.read(buffer)) > 0) {
                buffer.flip();
                sb.append(new String(buffer.array(),0,count));
                buffer.clear();
            }
            HttpHeaderParser parser = new HttpHeaderParser();
            header = parser.parse(sb.toString());
            System.out.println(header);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socketChannel != null)socketChannel.close();
            } catch (Exception e){
                e.printStackTrace();
            }
            return header;
        }
    }
}
