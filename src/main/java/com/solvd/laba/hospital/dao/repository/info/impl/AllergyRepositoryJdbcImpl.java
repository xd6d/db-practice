package com.solvd.laba.hospital.dao.repository.info.impl;

import com.solvd.laba.hospital.dao.ConnectionPool;
import com.solvd.laba.hospital.dao.repository.info.AllergyRepository;
import com.solvd.laba.hospital.model.info.Allergy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AllergyRepositoryJdbcImpl implements AllergyRepository {
    private static final Logger LOGGER = LogManager.getLogger(AllergyRepositoryJdbcImpl.class);
    private static final String CREATE = "INSERT INTO allergies(name, patient_id) VALUES (?, ?);";
    private static final String FIND_ALL_BY_PATIENT_ID = "SELECT * FROM allergies WHERE patient_id = ?;";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<Allergy> findAllByPatientId(long id) {
        List<Allergy> allergies = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_BY_PATIENT_ID)) {
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Allergy allergy = new Allergy();
                allergy.setId(rs.getLong("id"));
                allergy.setName(rs.getString("name"));
                allergies.add(allergy);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return allergies;
    }

    @Override
    public void create(Allergy allergy, long patientId) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, allergy.getName());
            ps.setLong(2, patientId);

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                allergy.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
