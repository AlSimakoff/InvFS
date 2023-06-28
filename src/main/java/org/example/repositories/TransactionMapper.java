package org.example.repositories;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TransactionMapper implements RowMapper<Transaction> {
    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Transaction(
                rs.getInt("id"),
                rs.getString("take"),
                rs.getString("delivery"),
                rs.getDate("date"),
                rs.getString("note")
        );
    }
}
