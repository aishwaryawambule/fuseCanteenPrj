package com.example.fuseCanteen.utils;

/**
 * Created by aishwarya on 9/5/20.
 */


import java.io.InputStream;
import java.util.Properties;

import org.slf4j.LoggerFactory;

public class PropertiesReader {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(JdbcConnection.class);
    private final static Properties configProp = new Properties();

    private PropertiesReader()
    {
        //Private constructor to restrict new instances
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("application.properties");

        try {
            configProp.load(in);
        } catch (Exception e) {
            LOGGER.error(":::::::::::::::::::::::::: Properties path is not correct ::::::::::::::::::::::"+e);
        }
    }

    public static String getProperty(String key){
        return configProp.getProperty(key);
    }



}
