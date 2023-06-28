package org.example.repositories;

import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ItemMapper implements RowMapper<item> {
    @Override
    public item mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new item(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("ser_numb"),
                rs.getString("inv_numb")
        );
    }
}

