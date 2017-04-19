package com.hfy.tomjetty.server;

import com.hfy.tomjetty.interfaces.*;
import com.hfy.tomjetty.valve.StandradWrapperValve;

/**
 * Created by HuangFangyuan on 2017/4/16.
 */
public class StandradWrapper implements Container,Pipeline,Lifecycle {

    private String name;
    private String servletClass;
    private Pipeline pipeline = new StandradPipeline(this);
    private Container parent;

    public StandradWrapper() {
        setBasic(new StandradWrapperValve());
    }

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

    @Override
    public void strat() {
        System.out.println("wrapper start");
        if (pipeline instanceof Lifecycle){
            ((Lifecycle) pipeline).strat();
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void addLifecycleListener(LifecycleListener listener) {

    }

    @Override
    public LifecycleListener[] findLifecycleListeners() {
        return new LifecycleListener[0];
    }
}
