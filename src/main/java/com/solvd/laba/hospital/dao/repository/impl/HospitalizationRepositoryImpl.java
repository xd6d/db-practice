package com.solvd.laba.hospital.dao.repository.impl;

import com.solvd.laba.hospital.dao.ConnectionPool;
import com.solvd.laba.hospital.dao.repository.HospitalizationRepository;
import com.solvd.laba.hospital.model.info.Hospitalization;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HospitalizationRepositoryImpl implements HospitalizationRepository {
    private static final Logger LOGGER = LogManager.getLogger(HospitalizationRepositoryImpl.class);
    private static final String FIND_ALL_BY_PATIENT_ID = "SELECT * FROM hospitalizations WHERE patient_id = ?;";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<Hospitalization> findAllByPatientId(long id) {
        List<Hospitalization> hospitalizations = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_BY_PATIENT_ID)) {
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Hospitalization hospitalization = new Hospitalization();
                hospitalization.setId(rs.getLong("id"));
                hospitalization.setPlace(rs.getString("place"));
                hospitalization.setDate(rs.getDate("date"));
                hospitalization.setCondition(rs.getString("patients_condition"));
                hospitalization.setDescription(rs.getString("description"));
                hospitalizations.add(hospitalization);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return hospitalizations;
    }
}
