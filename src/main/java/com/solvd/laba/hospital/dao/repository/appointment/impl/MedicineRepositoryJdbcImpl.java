package com.solvd.laba.hospital.dao.repository.appointment.impl;

import com.solvd.laba.hospital.dao.ConnectionPool;
import com.solvd.laba.hospital.dao.repository.appointment.MedicineRepository;
import com.solvd.laba.hospital.model.appointment.Medicine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class MedicineRepositoryJdbcImpl implements MedicineRepository {
    private static final Logger LOGGER = LogManager.getLogger(MedicineRepositoryJdbcImpl.class);
    private static final String CREATE = "INSERT INTO medicines(name, price, description, is_recepted) VALUES (?, ?, ?, ?);";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public Medicine create(Medicine medicine) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, medicine.getName());
            ps.setDouble(2, medicine.getPrice());
            ps.setString(3, medicine.getDescription());
            ps.setBoolean(4, medicine.isPrescribed());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                medicine.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return medicine;
    }
}
