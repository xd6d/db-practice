package com.solvd.laba.hospital.dao.repository.person.impl;

import com.solvd.laba.hospital.model.person.Position;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionTypeHandler extends BaseTypeHandler<Position> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Position parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getName());
    }

    @Override
    public Position getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return Position.fromName(rs.getString(columnName));
    }

    @Override
    public Position getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return Position.fromName(rs.getString(columnIndex));
    }

    @Override
    public Position getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Position.fromName(cs.getString(columnIndex));
    }
}
