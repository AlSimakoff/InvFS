package org.example.repositories;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class StoreRepositoryImpl implements StoreRepository{
    private static final String SQL_GET_BY_ID=
            "select id_item, Action, note from store where id = :id";
    private static final String SQL_Find_All=
            "select * from store";
    private static final String SQL_SAVE=
            "INSERT INTO store (`id_item`, 'Action', 'note') VALUES (:id, :Action, :note);";
    private static final String SQL_DELETE=
            "delete from store where id_item= :id";
    private final StoreMapper storeMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    public StoreRepositoryImpl(
            StoreMapper storeMapper,
            NamedParameterJdbcTemplate jdbcTemplate
    ){
        this.storeMapper = storeMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Store> getById(int id){
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbcTemplate.query(
                SQL_GET_BY_ID,
                params,
                storeMapper
        ).stream().findFirst();

    }
    @Override
    public void save(Store store){
        var params =new MapSqlParameterSource();
        params.addValue("id",store.id());
        params.addValue("Action",store.Action());
        params.addValue("note",store.note());
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
    public List<Store> findAll(){
        return jdbcTemplate.query(
                SQL_Find_All,
               storeMapper
        ).stream().toList();

    }



}
