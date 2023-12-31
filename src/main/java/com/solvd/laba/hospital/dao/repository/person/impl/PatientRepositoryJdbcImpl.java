package com.solvd.laba.hospital.dao.repository.person.impl;

import com.solvd.laba.hospital.dao.ConnectionPool;
import com.solvd.laba.hospital.dao.repository.person.PatientRepository;
import com.solvd.laba.hospital.model.person.PatientPerson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientRepositoryJdbcImpl implements PatientRepository {
    private static final Logger LOGGER = LogManager.getLogger(PatientRepositoryJdbcImpl.class);
    private static final String CREATE = "INSERT INTO patients(name, surname, phone_number, email) VALUES (?, ?, ?, ?);";
    private static final String FIND_ALL = "SELECT * FROM patients;";
    private static final String FIND_BY_ID = "SELECT * FROM patients WHERE id = ?;";
    private static final String UPDATE_BY_ID = "UPDATE patients SET name = ?, surname = ?, phone_number = ?, email = ? WHERE id = ?;";
    private static final String DELETE_BY_ID = "DELETE FROM patients WHERE id = ?;";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void create(PatientPerson patient) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, patient.getName());
            ps.setString(2, patient.getSurname());
            ps.setString(3, patient.getPhoneNumber());
            ps.setString(4, patient.getEmail());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                patient.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<PatientPerson> findAll() {
        List<PatientPerson> patients = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PatientPerson patient = new PatientPerson();
                patient.setId(rs.getLong("id"));
                patient.setName(rs.getString("name"));
                patient.setSurname(rs.getString("surname"));
                patient.setPhoneNumber(rs.getString("phone_number"));
                patient.setEmail(rs.getString("email"));
                patients.add(patient);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return patients;
    }

    @Override
    public Optional<PatientPerson> findById(long id) {
        PatientPerson patient = null;
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                patient = new PatientPerson();
                patient.setId(rs.getLong("id"));
                patient.setName(rs.getString("name"));
                patient.setSurname(rs.getString("surname"));
                patient.setPhoneNumber(rs.getString("phone_number"));
                patient.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return Optional.ofNullable(patient);
    }

    @Override
    public void update(PatientPerson patient) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_BY_ID)) {
            ps.setString(1, patient.getName());
            ps.setString(2, patient.getSurname());
            ps.setString(3, patient.getPhoneNumber());
            ps.setString(4, patient.getEmail());
            ps.setLong(5, patient.getId());

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
        try (PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID)) {
            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
