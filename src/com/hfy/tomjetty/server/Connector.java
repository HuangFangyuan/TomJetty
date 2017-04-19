package com.hfy.tomjetty.server;

import com.hfy.tomjetty.interfaces.Container;
import com.hfy.tomjetty.utils.TomJettyUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.*;

/**
 * Created by HuangFangyuan on 2017/4/15.
 */
public class Connector implements Runnable{

    private ServerSocketChannel server;
    private boolean shutdown = false;
    private Container container;
    private ExecutorService executorService = Executors.newFixedThreadPool(5);

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
                if (shutdown)break;
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
//                        HttpRequestHeader header = processor.process(socketChannel);
                        processor.setSocketChannel(socketChannel);
                        Future<HttpRequestHeader> future = executorService.submit(processor);
                        HttpServletRequest request = getRequest();
                        try {
                            request.setHeader(future.get());
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                        HttpServletResponse response = getResponse();
                        container.invoke(request,response);
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
        await();
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public HttpServletRequest getRequest(){
        return new HttpServletRequest();
    }

    public HttpServletResponse getResponse(){
        return new HttpServletResponse();
    }



}
