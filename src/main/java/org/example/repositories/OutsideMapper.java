package org.example.repositories;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OutsideMapper implements RowMapper<Outside> {
    @Override
    public Outside mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Outside(
                rs.getInt("id_item"),
                rs.getString("Date_delivery"),
                rs.getString("Date_take"),
                rs.getString("Action"),
                rs.getString("note")
        );
    }
}
