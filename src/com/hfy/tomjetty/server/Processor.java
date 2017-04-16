package com.hfy.tomjetty.server;

import com.hfy.tomjetty.utils.TomJettyUtil;

import java.io.File;
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
    public void process(SocketChannel socketChannel){
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
            HttpRequestHeader header = parser.parse(sb.toString());
            System.out.println(header);
            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            File file = new File(TomJettyUtil.getValue("tomjetty.webapps"),header.getUrl());

            socketChannel.write(writeBuffer);
            buffer.clear();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socketChannel != null)socketChannel.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
