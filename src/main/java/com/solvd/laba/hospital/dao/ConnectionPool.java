package com.solvd.laba.hospital.dao;

import com.solvd.laba.hospital.model.exceptions.CreateConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private volatile static ConnectionPool instance;
    private final Queue<Connection> connections = new ConcurrentLinkedQueue<>();

    private ConnectionPool(int size) {
        for (int i = 0; i < size; i++) {
            try {
                connections.add(DriverManager.getConnection(Config.URL, Config.USER, Config.PASSWORD));
            } catch (SQLException e) {
                throw new CreateConnectionException(e);
            }
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool(Config.POOL_SIZE);
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        Connection connection = connections.poll();
        while (connection == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
            connection = connections.poll();
        }
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) {
        connections.add(connection);
        notify();
    }
}
