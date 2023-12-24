package com.solvd.laba.hospital.dao.repository.info.impl;

import com.solvd.laba.hospital.dao.ConnectionPool;
import com.solvd.laba.hospital.dao.repository.info.DeclarationRepository;
import com.solvd.laba.hospital.model.info.Declaration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Optional;

public class DeclarationRepositoryImpl implements DeclarationRepository {
    private static final Logger LOGGER = LogManager.getLogger(DeclarationRepositoryImpl.class);
    private static final String CREATE = "INSERT INTO declarations(doctor_id, created, expires) VALUES (?, ?, ?);";
    private static final String UPDATE_PATIENT = "UPDATE patients SET declaration_id = ? WHERE id = ?;";
    private static final String FIND_DECLARATION_ID_BY_PATIENT_ID = "SELECT declaration_id FROM patients WHERE id = ?;";
    private static final String FIND_DECLARATION_BY_ID = "SELECT * FROM declarations WHERE id = ?;";
    private static final String UPDATE = "UPDATE declarations SET expires = ? WHERE id = ?;";
    private static final String DELETE = "DELETE FROM declarations WHERE id = ?;";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public Declaration create(Declaration declaration, long patientId) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement create = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement updatePatient = connection.prepareStatement(UPDATE_PATIENT)) {
            connection.setAutoCommit(false);
            create.setLong(1, declaration.getDoctor().getId());
            create.setDate(2, new Date(declaration.getCreated().getTime()));
            create.setDate(3, new Date(declaration.getExpires().getTime()));

            create.executeUpdate();
            ResultSet rs = create.getGeneratedKeys();
            while (rs.next()) {
                declaration.setId(rs.getLong(1));
            }
//todo test
            updatePatient.setLong(2, patientId);
            updatePatient.setLong(1, declaration.getId());
            updatePatient.executeUpdate();
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
        return declaration;
    }

    @Override
    public Optional<Declaration> findByPatientId(long patientId) {
        Declaration declaration = null;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement findId = connection.prepareStatement(FIND_DECLARATION_ID_BY_PATIENT_ID);
             PreparedStatement findEntity = connection.prepareStatement(FIND_DECLARATION_BY_ID)) {
            findId.setLong(1, patientId);

            ResultSet findIdResultSet = findId.executeQuery();
            long id = 0;
            while (findIdResultSet.next()) {
                id = findIdResultSet.getLong("declaration_id");
            }
            findEntity.setLong(1, id);

            ResultSet findEntityResultSet = findEntity.executeQuery();
            while (findEntityResultSet.next()) {
                declaration = new Declaration();
                declaration.setId(findEntityResultSet.getLong("id"));
                declaration.setCreated(findEntityResultSet.getDate("created"));
                declaration.setExpires(findEntityResultSet.getDate("expires"));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return Optional.ofNullable(declaration);
    }

    @Override
    public void update(Declaration declaration) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setDate(1, new Date(declaration.getExpires().getTime()));
            ps.setLong(2, declaration.getId());

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