package com.solvd.laba.hospital.dao.repository.info.impl;

import com.solvd.laba.hospital.dao.ConnectionPool;
import com.solvd.laba.hospital.dao.repository.info.VaccinationRepository;
import com.solvd.laba.hospital.model.info.Vaccination;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VaccinationRepositoryImpl implements VaccinationRepository {
    private static final Logger LOGGER = LogManager.getLogger(VaccinationRepositoryImpl.class);
    private static final String FIND_ALL_BY_PATIENT_ID = "SELECT * FROM vaccinations WHERE patient_id = ?;";

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
}
