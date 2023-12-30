package com.solvd.laba.hospital.dao.repository.info.impl;

import com.solvd.laba.hospital.dao.ConnectionPool;
import com.solvd.laba.hospital.dao.repository.info.VaccinationRepository;
import com.solvd.laba.hospital.model.info.Vaccination;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VaccinationRepositoryJdbcImpl implements VaccinationRepository {
    private static final Logger LOGGER = LogManager.getLogger(VaccinationRepositoryJdbcImpl.class);
    private static final String FIND_ALL_BY_PATIENT_ID = "SELECT * FROM vaccinations WHERE patient_id = ?;";
    private static final String CREATE = "INSERT INTO vaccinations(name, date, expires, patient_id) VALUES (?, ?, ?, ?);";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<Vaccination> findAllByPatientId(long id) {
        List<Vaccination> vaccinations = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_BY_PATIENT_ID)) {
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vaccination vaccination = new Vaccination();
                vaccination.setId(rs.getLong("id"));
                vaccination.setName(rs.getString("name"));
                vaccination.setDate(rs.getDate("date"));
                vaccination.setExpires(rs.getDate("expires"));
                vaccinations.add(vaccination);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return vaccinations;
    }

    @Override
    public void create(Vaccination vaccination, long patientId) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, vaccination.getName());
            ps.setDate(2, new Date(vaccination.getDate().getTime()));
            ps.setDate(3, new Date(vaccination.getExpires().getTime()));
            ps.setLong(4, patientId);

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                vaccination.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
