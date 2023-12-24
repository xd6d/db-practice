package com.solvd.laba.hospital.dao.repository.info.impl;

import com.solvd.laba.hospital.dao.ConnectionPool;
import com.solvd.laba.hospital.dao.repository.info.HospitalizationRepository;
import com.solvd.laba.hospital.model.info.Hospitalization;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalizationRepositoryImpl implements HospitalizationRepository {
    private static final Logger LOGGER = LogManager.getLogger(HospitalizationRepositoryImpl.class);
    private static final String FIND_ALL_BY_PATIENT_ID = "SELECT * FROM hospitalizations WHERE patient_id = ?;";
    private static final String CREATE = "INSERT INTO hospitalizations(place, date, patients_condition, description, patient_id) VALUES (?, ?, ?, ?, ?);";

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

    @Override
    public Hospitalization create(Hospitalization hospitalization, long patientId) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, hospitalization.getPlace());
            ps.setDate(2, new Date(hospitalization.getDate().getTime()));
            ps.setString(3, hospitalization.getCondition());
            ps.setString(4, hospitalization.getDescription());
            ps.setLong(5, patientId);

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                hospitalization.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return hospitalization;
    }
}
