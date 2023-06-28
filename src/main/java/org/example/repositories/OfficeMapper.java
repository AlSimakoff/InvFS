package org.example.repositories;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OfficeMapper implements RowMapper<Office> {
    @Override
    public Office mapRow(ResultSet rs, int rowNum) throws SQLException{
    return new Office(
            rs.getInt("id"),
            rs.getString("delivery"),
            rs.getString("action"),
            rs.getString("note")
    );
    }
}
