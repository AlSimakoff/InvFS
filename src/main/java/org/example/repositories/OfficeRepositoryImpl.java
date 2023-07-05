package org.example.repositories;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class OfficeRepositoryImpl implements OfficeRepository{
    private static final String SQL_GET_BY_ID=
            "select id, Date_delivery, Action, note from office where id = :id";
    private static final String SQL_Find_All=
            "select * from office";
    private static final String SQL_SAVE=
            "INSERT INTO office (`id`, `Date_delivery', 'Action', 'note') VALUES (:id, :Date_delivery, :Action, :note);";
    private static final String SQL_DELETE=
            "delete from office where id= :id";
    private final OfficeMapper officeMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    public OfficeRepositoryImpl(
            OfficeMapper officeMapper,
            NamedParameterJdbcTemplate jdbcTemplate
    ){
        this.officeMapper = officeMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Office> getById(int id){
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbcTemplate.query(
                SQL_GET_BY_ID,
                params,
                officeMapper
        ).stream().findFirst();

    }
    @Override
    public void save(Office office){
        var params =new MapSqlParameterSource();
        params.addValue("id",office.id());
        params.addValue("Date_delivery",office.Date_delivery());
        params.addValue("Action",office.Action());
        params.addValue("note",office.note());
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
    public List<Office> findAll(){
        return jdbcTemplate.query(
                SQL_Find_All,
                officeMapper
        ).stream().toList();

    }



}
