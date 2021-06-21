package org.apache.flink.training.exercises.common.utils;

import java.io.InputStreamReader;
import java.util.Properties;

public class AvalonConfig {
    private static Properties properties;

    public synchronized static Properties getProperties(String propertyName) {
        if (null != properties) {
            return properties;
        }
        //获取配置文件kafka.properties的内容。
        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(AvalonConfig.class.getClassLoader().getResourceAsStream(propertyName),"utf-8"));
        } catch (Exception e) {
            //没加载到文件，程序要考虑退出。
            e.printStackTrace();
        }
        AvalonConfig.properties = properties;
        return properties;
    }
}