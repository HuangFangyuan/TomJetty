package com.hfy.tomjetty.client;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by HuangFangyuan on 2017/4/15.
 */
public class Client {

    public void sendMessage(){

        SocketChannel client = null;
        try {
            client = SocketChannel.open(new InetSocketAddress("127.0.0.1",10086));
            client.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("GET /hello.jsp HTTP/1.1 ".getBytes());
            buffer.flip();
            client.write(buffer);
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
       finally {
            if (client != null){
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
