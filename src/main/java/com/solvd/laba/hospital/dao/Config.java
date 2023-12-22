package com.solvd.laba.hospital.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final Logger LOGGER = LogManager.getLogger(Config.class);
    public static String URL;
    public static String USER;
    public static String PASSWORD;
    public static int POOL_SIZE;


    static {
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            Properties properties = new Properties();
            properties.load(fis);

            URL = properties.getProperty("url");
            USER = properties.getProperty("user");
            PASSWORD = properties.getProperty("password");
            POOL_SIZE = Integer.parseInt(properties.getProperty("pool-size"));
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}
