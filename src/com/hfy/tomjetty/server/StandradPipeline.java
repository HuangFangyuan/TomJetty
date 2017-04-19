package com.hfy.tomjetty.server;

import com.hfy.tomjetty.interfaces.*;

/**
 * Created by HuangFangyuan on 2017/4/17.
 */
public class StandradPipeline implements Pipeline,Lifecycle {

    private Container container = null;
    private Valve basic = null;
    private Valve[] valves = new Valve[0];

    StandradPipeline(Container container){
        this.container = container;
    }

    @Override
    public void invoke(Request request, Response response) {
        (new StandardValveContext()).invokeNext(request,response);
    }

    @Override
    public void addValve(Valve valve) {
        valve.setContainer(this.container);

        synchronized (valves){
            Valve[] newValavs = new Valve[valves.length+1];
            System.arraycopy(valves,0,newValavs,0,valves.length);
            newValavs[valves.length] = valve;
            valves = newValavs;
        }
    }

    @Override
    public void removeValve(Valve valve) {

    }

    @Override
    public void setBasic(Valve valve) {
        basic = valve;
    }

    @Override
    public Valve getBasic() {
        return basic;
    }

    @Override
    public void strat() {
        System.out.println("pipline start");
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

    class StandardValveContext implements ValveContext {

        private int stage = 0;

        @Override
        public void invokeNext(Request request, Response response) {
            int subscript = stage;
            stage = stage + 1;
            if (subscript < valves.length){
                valves[subscript].invoke(request,response,this);
            }
            else if ((subscript == valves.length) && basic != null){
                basic.invoke(request,response,this);
            }
            else {
                throw new RuntimeException("No basic valve");
            }
        }
    }
}
