package com.hfy.tomjetty.server;

import com.hfy.tomjetty.interfaces.*;

/**
 * Created by HuangFangyuan on 2017/4/17.
 */
public class SimplePipeline implements Pipeline {

    private Container container = null;
    private Valve basic = null;
    private Valve[] valves = new SimpleValve[0];

    SimplePipeline(Container container){
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
            Valve[] newArray = new SimpleValve[valves.length+1];
            System.arraycopy(valves,0,newArray,0,valves.length);
            newArray[valves.length] = valve;
            valves = newArray;
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
