package com.hfy.tomjetty.server;

import com.hfy.tomjetty.utils.TomJettyUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by HuangFangyuan on 2017/4/15.
 */
public class Connector implements Runnable{

    private ServerSocketChannel server;
    private boolean shutdown = false;

    public void await(){
        try {
            server = ServerSocketChannel.open();
            //设置为非阻塞
            server.configureBlocking(false);
            int port = Integer.parseInt(TomJettyUtil.getValue("tomjetty.port"));
            server.bind(new InetSocketAddress(10086));
            //选择器
            Selector selector = Selector.open();
            //注册感兴趣的事件
            server.register(selector, SelectionKey.OP_ACCEPT);
            //选择已就绪的事件，阻塞直到一个就绪
            while (selector.select()>0){
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()){
                    SelectionKey key = keys.next();
                    if (key.isAcceptable()){
                        SocketChannel socketChannel = server.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector,SelectionKey.OP_READ);
                    }
                    else if (key.isReadable()){
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        Processor processor = new Processor(this);
                        processor.process(socketChannel);
                    }
                    //删除
                    keys.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!shutdown){
            await();
        }
    }

}
