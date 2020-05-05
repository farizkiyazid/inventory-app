package com.example.inventoryapp.mapper;

import com.example.inventoryapp.model.Item;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ItemMapper {
    final String getAll = "SELECT * FROM inventory";
    final String getById = "SELECT * FROM inventory WHERE idItem = #{idItem}";
    final String deleteById = "DELETE from inventory WHERE idItem = #{idItem}";
    final String insert = "INSERT INTO inventory (itemType) VALUES (#{itemType})";
    final String update = "UPDATE inventory SET itemType = #{itemType}, available = #{available} WHERE idItem = #{idItem}";

    @Select(getAll)
    List<Item> getAll();

    @Select(getById)
    @Results(value = {
            @Result(property = "idItem", column = "idItem"),
            @Result(property = "itemType", column = "itemType"),
            @Result(property = "available", column = "available")
    })
    Item getById(long idItem);

    @Update(update)
    void update(Item item);

    @Delete(deleteById)
    void delete(long idItem);

    @Insert(insert)
    @Options(useGeneratedKeys = true, keyProperty = "idItem")
    void insert(Item item);
}
