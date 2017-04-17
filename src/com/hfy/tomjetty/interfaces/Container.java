package com.hfy.tomjetty.interfaces;

/**
 * Created by HuangFangyuan on 2017/4/16.
 */
public interface Container {
    //添加子容器
    void addChild(Container container);
    //删除子容器
    void removeChild(Container container);
    //返回指定子容器
    Container findChild(String name);
    //返回所有子容器
    Container[] findChildren();
    void invoke(Request request,Response response);
    void setName(String name);
    String getName();
    void setParent(Container container);
    Container getParent();
}
