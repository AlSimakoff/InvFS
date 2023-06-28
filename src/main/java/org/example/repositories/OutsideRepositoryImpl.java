package org.example.repositories;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Optional;

public class OutsideRepositoryImpl implements OutsideRepository{
    private static final String SQL_GET_BY_ID=
            "select id, Date_delivery, Date_take, Action, note from outside where id = :id";
    private static final String SQL_Find_All=
            "select * from outside";
    private static final String SQL_SAVE=
            "INSERT INTO outside (`id`, 'Date_delivery', 'Date_take', 'Action', 'note') VALUES (:id, :date, :take, :Action, :note);";
    private static final String SQL_DELETE=
            "delete from outside where id= :id";
    private final OutsideMapper outsideMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    public OutsideRepositoryImpl(
            OutsideMapper outsideMapper,
            NamedParameterJdbcTemplate jdbcTemplate
    ){
        this.outsideMapper = outsideMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Outside> getById(int id){
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbcTemplate.query(
                SQL_GET_BY_ID,
                params,
                outsideMapper
        ).stream().findFirst();

    }
    @Override
    public void save(Outside outside){
        var params =new MapSqlParameterSource();
        params.addValue("id",outside.id());
        params.addValue("Date_delivery",outside.delivery());
        params.addValue("take",outside.take());
        params.addValue("Action",outside.action());
        params.addValue("note",outside.note());
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
    public List<Outside> findAll(){
        return jdbcTemplate.query(
                SQL_Find_All,
                outsideMapper
        ).stream().toList();

    }



}
