package com.solvd.laba.hospital.dao.repository.appointment.impl;

import com.solvd.laba.hospital.dao.ConnectionPool;
import com.solvd.laba.hospital.dao.repository.appointment.MedicalRepository;
import com.solvd.laba.hospital.model.appointment.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class MedicalRepositoryImpl implements MedicalRepository {
    private static final Logger LOGGER = LogManager.getLogger(MedicalRepositoryImpl.class);
    private static final String CREATE = "INSERT INTO services(name, price, description) VALUES (?, ?, ?);";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public Service create(Service medicalService) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, medicalService.getName());
            ps.setDouble(2, medicalService.getPrice());
            ps.setString(3, medicalService.getDescription());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                medicalService.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return medicalService;
    }
}
