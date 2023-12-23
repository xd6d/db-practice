package com.solvd.laba.hospital.dao.repository.impl;

import com.solvd.laba.hospital.dao.ConnectionPool;
import com.solvd.laba.hospital.dao.repository.AnalysisRepository;
import com.solvd.laba.hospital.model.info.Analysis;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnalysisRepositoryImpl implements AnalysisRepository {
    private static final Logger LOGGER = LogManager.getLogger(AnalysisRepositoryImpl.class);
    private static final String FIND_ALL_BY_PATIENT_ID = "SELECT * FROM analyses WHERE patient_id = ?;";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<Analysis> findAllByPatientId(long id) {
        List<Analysis> analyses = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_BY_PATIENT_ID)) {
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Analysis analysis = new Analysis();
                analysis.setId(rs.getLong("id"));
                analysis.setName(rs.getString("name"));
                analysis.setValue(rs.getString("value"));
                analysis.setUnit(rs.getString("unit"));
                analysis.setHealthyValue(rs.getString("healthy_value"));
                analyses.add(analysis);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return analyses;
    }
}
