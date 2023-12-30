package com.solvd.laba.hospital.dao;

import com.solvd.laba.hospital.model.exceptions.SessionFactoryException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Logger LOGGER = LogManager.getLogger(Config.class);
    private static final SqlSessionFactory SESSION_FACTORY;
    public static String URL;
    public static String USER;
    public static String PASSWORD;
    public static int POOL_SIZE;

    static {
        try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {
            SESSION_FACTORY = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            throw new SessionFactoryException(e);
        }
    }

    static {
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            Properties properties = new Properties();
            properties.load(fis);

            URL = properties.getProperty("url");
            USER = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");
            POOL_SIZE = Integer.parseInt(properties.getProperty("pool-size"));
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    public static SqlSessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
