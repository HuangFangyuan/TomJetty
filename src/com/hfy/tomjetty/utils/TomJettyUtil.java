package com.hfy.tomjetty.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by HuangFangyuan on 2017/4/11.
 */
public class TomJettyUtil {

    private static Properties props = new Properties();
    static {
        try {
            URL url = ClassLoader.getSystemClassLoader().getResource("tomjetty.config");
//            System.out.println(url);
            props.load(new FileInputStream(url.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    public static String getValue(String key){
        return props.getProperty(key);
    }
}
