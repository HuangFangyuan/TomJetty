package com.hfy.tomjetty.server;

import com.hfy.tomjetty.interfaces.*;

/**
 * Created by HuangFangyuan on 2017/4/16.
 */
public class SimpleWrapper implements Container,Pipeline {

    private String name;
    private Pipeline pipeline = new SimplePipeline(this);
    private Container parent;

    @Override
    public void addChild(Container container) {

    }

    @Override
    public void removeChild(Container container) {

    }

    @Override
    public Container findChild(String name) {
        return null;
    }

    @Override
    public Container[] findChildren() {
        return new Container[0];
    }

    @Override
    public void invoke(Request request, Response response) {
        pipeline.invoke(request,response);
    }

    @Override
    public void addValve(Valve valve) {
        pipeline.addValve(valve);
    }

    @Override
    public void removeValve(Valve valve) {

    }

    @Override
    public void setBasic(Valve valve) {
        pipeline.setBasic(valve);
    }

    @Override
    public Valve getBasic() {
        return pipeline.getBasic();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setParent(Container container) {
        this.parent = parent;
    }

    @Override
    public Container getParent() {
        return parent;
    }
}
