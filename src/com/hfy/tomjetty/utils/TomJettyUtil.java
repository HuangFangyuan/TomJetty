package com.hfy.tomjetty.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by HuangFangyuan on 2017/4/11.
 */
public class TomJettyUtil {

    private static Properties props = new Properties();

    static {
        try {
            props.load(new FileInputStream("com/hfy/tomjetty/tomjetty.config"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    public static String getValue(String key){
        return props.getProperty(key);
    }
}
