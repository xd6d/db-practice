package com.solvd.laba.hospital.dao.repository.impl;

import com.solvd.laba.hospital.dao.ConnectionPool;
import com.solvd.laba.hospital.dao.repository.DeclarationRepository;
import com.solvd.laba.hospital.model.info.Declaration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DeclarationRepositoryImpl implements DeclarationRepository {
    private static final Logger LOGGER = LogManager.getLogger(DeclarationRepositoryImpl.class);
    private static final String FIND_DECLARATION_ID_BY_PATIENT_ID = "SELECT declaration_id FROM patients WHERE id = ?;";
    private static final String FIND_DECLARATION_BY_ID = "SELECT * FROM declarations WHERE id = ?;";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

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
}
