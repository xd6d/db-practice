package com.solvd.laba.hospital.dao.repository.appointment.impl;

import com.solvd.laba.hospital.dao.ConnectionPool;
import com.solvd.laba.hospital.dao.repository.appointment.AppointmentRepository;
import com.solvd.laba.hospital.model.appointment.Appointment;
import com.solvd.laba.hospital.model.appointment.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class AppointmentRepositoryJdbcImpl implements AppointmentRepository {
    private static final Logger LOGGER = LogManager.getLogger(AppointmentRepositoryJdbcImpl.class);
    private static final String CREATE = "INSERT INTO appointments(time, patient_id, doctor_id) VALUES (?, ?, ?);";
    private static final String SAVE_SERVICES = "INSERT INTO appointment_services(appointment_id, service_id) VALUES (?, ?);";
    private static final String EXISTS_BY_ID = "SELECT * FROM appointments WHERE id = ?";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void create(Appointment appointment) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement createApp = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement saveServices = connection.prepareStatement(SAVE_SERVICES)) {
            connection.setAutoCommit(false);
            createApp.setDate(1, new Date(appointment.getTime().getTime()));
            createApp.setLong(2, appointment.getPatient().getId());
            createApp.setLong(3, appointment.getDoctor().getId());

            createApp.executeUpdate();
            ResultSet rs = createApp.getGeneratedKeys();
            while (rs.next()) {
                appointment.setId(rs.getLong(1));
            }

            saveServices.setLong(1, appointment.getId());
            for (Service medicalService : appointment.getServices()) {
                saveServices.setLong(2, medicalService.getId());
                saveServices.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOGGER.error(e);
            }
            LOGGER.error(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOGGER.error(e);
            }
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public boolean existsById(long id) {
        boolean exists = false;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(EXISTS_BY_ID)) {
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            exists = rs.next();
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return exists;
    }
}
