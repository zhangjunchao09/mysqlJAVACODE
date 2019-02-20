package com.zhangjc.mysql.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

public class PropertiesUtil {

    public static HashMap<String, String> propertyMap;

    static {
        propertyMap = new HashMap<>();
        Properties pro = new Properties();
        InputStream in;
        try {
            in = PropertiesUtil.class.getClassLoader().getResourceAsStream("mysql.properties");
            pro.load(in);
            Iterator<String> it = pro.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                propertyMap.put(key, pro.getProperty(key));
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getPropertiesByName(String name) {
        return propertyMap.get(name);
    }
}
