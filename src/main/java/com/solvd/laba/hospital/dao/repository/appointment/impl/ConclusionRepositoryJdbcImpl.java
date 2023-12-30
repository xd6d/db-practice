package com.solvd.laba.hospital.dao.repository.appointment.impl;

import com.solvd.laba.hospital.dao.ConnectionPool;
import com.solvd.laba.hospital.dao.repository.appointment.ConclusionRepository;
import com.solvd.laba.hospital.model.appointment.Conclusion;
import com.solvd.laba.hospital.model.appointment.Medicine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class ConclusionRepositoryJdbcImpl implements ConclusionRepository {
    private static final Logger LOGGER = LogManager.getLogger(ConclusionRepositoryJdbcImpl.class);
    private static final String CREATE = "INSERT INTO conclusions(complaint, medical_history, observation, diagnosis, recommendations) VALUES (?, ?, ?, ?, ?);";
    private static final String SAVE_MEDICINES = "INSERT INTO conclusion_medicines(conclusion_id, medicine_id) VALUES (?, ?);";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public Conclusion create(Conclusion conclusion, long appointmentId) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement createConclusion = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement saveMedicines = connection.prepareStatement(SAVE_MEDICINES)) {
            connection.setAutoCommit(false);
            createConclusion.setString(1, conclusion.getComplaint());
            createConclusion.setString(2, conclusion.getMedicalHistory());
            createConclusion.setString(3, conclusion.getObservation());
            createConclusion.setString(4, conclusion.getDiagnosis());
            createConclusion.setString(5, conclusion.getRecommendation());
            createConclusion.setLong(5, appointmentId);

            createConclusion.executeUpdate();
            ResultSet rs = createConclusion.getGeneratedKeys();
            while (rs.next()) {
                conclusion.setId(rs.getLong(1));
            }

            saveMedicines.setLong(1, conclusion.getId());
            for (Medicine medicine : conclusion.getMedicines()) {
                saveMedicines.setLong(2, medicine.getId());
                saveMedicines.executeUpdate();
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
        return conclusion;
    }
}
