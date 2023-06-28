package org.example.repositories;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class itemRepositoryImpl implements itemRepository{
    private static final String SQL_GET_ITEM_BY_ID=
            "select id, name, ser_numb, inv_numb from items where id = :id";
    private static final String SQL_Find_All=
            "select * from items";
    private static final String SQL_SAVE_ITEM=
            "INSERT INTO `inventory`.`items` (`id`, `name`, `ser_numb`, `inv_numb`) VALUES (:id, :name, :ser, :inv);";
    private static final String SQL_DELETE=
            "delete from items where id= :id";
    private final ItemMapper itemMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    public itemRepositoryImpl(
            ItemMapper itemMapper,
            NamedParameterJdbcTemplate jdbcTemplate
    ){
    this.itemMapper = itemMapper;
    this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<item> getitemById(int id){
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbcTemplate.query(
                SQL_GET_ITEM_BY_ID,
                params,
                itemMapper
        ).stream().findFirst();

    }
    @Override
    public void save(item Item){
        var params =new MapSqlParameterSource();
        params.addValue("id",Item.id());
        params.addValue("name",Item.name());
        params.addValue("ser",Item.ser_numb());
        params.addValue("inv",Item.inv_numb());
        jdbcTemplate.update(
                SQL_SAVE_ITEM,
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
    public List<item> findAll(){
        return jdbcTemplate.query(
                SQL_Find_All,
                itemMapper
        ).stream().toList();

    }



}
