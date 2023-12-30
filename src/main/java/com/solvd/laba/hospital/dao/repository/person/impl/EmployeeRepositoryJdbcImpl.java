package com.solvd.laba.hospital.dao.repository.person.impl;

import com.solvd.laba.hospital.dao.ConnectionPool;
import com.solvd.laba.hospital.dao.repository.person.EmployeeRepository;
import com.solvd.laba.hospital.model.person.EmployeePerson;
import com.solvd.laba.hospital.model.person.Position;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryJdbcImpl implements EmployeeRepository {
    private static final Logger LOGGER = LogManager.getLogger(EmployeeRepositoryJdbcImpl.class);
    private static final String CREATE = "INSERT INTO employees(name, surname, phone_number, email, degree, position_name) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String FIND_ALL = "SELECT * FROM employees;";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void create(EmployeePerson employee) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getSurname());
            ps.setString(3, employee.getPhoneNumber());
            ps.setString(4, employee.getEmail());
            ps.setString(5, employee.getDegree());
            ps.setString(6, employee.getPosition().getName());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                employee.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<EmployeePerson> findAll() {
        List<EmployeePerson> employees = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EmployeePerson employee = new EmployeePerson();
                employee.setId(rs.getLong("id"));
                employee.setName(rs.getString("name"));
                employee.setSurname(rs.getString("surname"));
                employee.setPhoneNumber(rs.getString("phone_number"));
                employee.setEmail(rs.getString("email"));
                employee.setDegree(rs.getString("degree"));
                employee.setPosition(Position.valueOf(rs.getString("position_name").toUpperCase()));
                employees.add(employee);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return employees;
    }
}
