package com.solvd.laba.hospital.dao.repository.info.impl;

import com.solvd.laba.hospital.dao.ConnectionPool;
import com.solvd.laba.hospital.dao.repository.info.AnalysisRepository;
import com.solvd.laba.hospital.model.info.Analysis;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnalysisRepositoryJdbcImpl implements AnalysisRepository {
    private static final Logger LOGGER = LogManager.getLogger(AnalysisRepositoryJdbcImpl.class);
    private static final String CREATE = "INSERT INTO analyses(name, value, unit, healthy_value, patient_id) VALUES (?, ?, ?, ?, ?);";
    private static final String FIND_ALL_BY_PATIENT_ID = "SELECT * FROM analyses WHERE patient_id = ?;";
    private static final String UPDATE = "UPDATE analyses SET name = ?, value = ?, unit = ?, healthy_value = ? WHERE id = ?;";
    private static final String DELETE = "DELETE FROM analyses WHERE id = ?;";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void create(Analysis analysis, long patientId) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, analysis.getName());
            ps.setString(2, analysis.getValue());
            ps.setString(3, analysis.getUnit());
            ps.setString(4, analysis.getHealthyValue());
            ps.setLong(5, patientId);

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                analysis.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

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

    @Override
    public void update(Analysis analysis) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, analysis.getName());
            ps.setString(2, analysis.getValue());
            ps.setString(3, analysis.getUnit());
            ps.setString(4, analysis.getHealthyValue());
            ps.setLong(5, analysis.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(long id) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
