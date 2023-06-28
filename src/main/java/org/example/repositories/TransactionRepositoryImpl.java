package org.example.repositories;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Optional;

public class TransactionRepositoryImpl implements TransactionRepository{
    private static final String SQL_GET_BY_ID=
            "select id, from_take, to_delivery, date_delivery, note from transaction where id = :id";
    private static final String SQL_Find_All=
            "select * from transaction";
    private static final String SQL_SAVE=
            "INSERT INTO transaction (`id`, 'from_take', 'to_delivery', 'date_delivery', 'note') VALUES (:id, :take, :delivery, :date, :note);";
    private static final String SQL_DELETE=
            "delete from transaction where id= :id";
    private final TransactionMapper transactionMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    public TransactionRepositoryImpl(
            TransactionMapper transactionMapper,
            NamedParameterJdbcTemplate jdbcTemplate
    ){
        this.transactionMapper = transactionMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Transaction> getById(int id){
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbcTemplate.query(
                SQL_GET_BY_ID,
                params,
                transactionMapper
        ).stream().findFirst();

    }
    @Override
    public void save(Transaction transaction){
        var params =new MapSqlParameterSource();
        params.addValue("id",transaction.id());
        params.addValue("take",transaction.take());
        params.addValue("delivery",transaction.delivery());
        params.addValue("date",transaction.date());
        params.addValue("note",transaction.note());
        jdbcTemplate.update(
                SQL_SAVE,
                params
        );

    }
    @Override
    public void delete(int id){
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(
                SQL_DELETE,
                params);
    }
    @Override
    public List<Transaction> findAll(){
        return jdbcTemplate.query(
                SQL_Find_All,
               transactionMapper
        ).stream().toList();

    }



}
