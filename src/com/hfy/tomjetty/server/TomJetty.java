package com.hfy.tomjetty.server;

import com.hfy.tomjetty.client.Client;
import com.hfy.tomjetty.utils.TomJettyUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by HuangFangyuan on 2017/4/15.
 */
public class TomJetty implements Runnable{

    private ServerSocketChannel server;

    @Override
    public void run() {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
//            int port = Integer.parseInt(TomJettyUtil.getValue("tomjetty.port"));
            server.bind(new InetSocketAddress(9611));
            //选择器
            Selector selector = Selector.open();
            SelectionKey i =server.register(selector, SelectionKey.OP_ACCEPT);
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
                        handleRead(socketChannel);
                    }
                }
                keys.remove();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handleRead(SocketChannel socketChannel){

        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (socketChannel.read(buffer)>0) {
                buffer.flip();
                System.out.println(new String(buffer.array()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void openServer(){
        new Thread(new TomJetty()).start();
    }

    public static void main(String[] args) {
        TomJetty.openServer();
        Client client = new Client();
    }
}
