package com.example.inventoryapp.mapper;

import com.example.inventoryapp.model.InventoryAlloc;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface InventoryAllocMapper {
    /**
     * Shared table yang bisa diakses kedua microservices.
     */
    final String getAll = "SELECT * FROM invAlloc";
    final String getById = "SELECT * FROM invAlloc WHERE idInvAlloc = #{idInvAlloc}";
    final String getByItem = "SELECT * FROM invAlloc WHERE idItem = #{idItem}";
    final String getAllByStaff = "SELECT * FROM invAlloc WHERE idStaff = #{idStaff}";
    final String insert = "INSERT INTO invAlloc (idStaff, idItem) VALUES (#{idStaff}, #{idItem})";

    @Select(getAll)
    List<InventoryAlloc> getAll();

    @Select(getById)
    @Results(value = {
            @Result(property = "idInvAlloc", column = "idInvAlloc"),
            @Result(property = "idStaff", column = "idStaff"),
            @Result(property = "idItem", column = "idItem"),
            @Result(property = "timeAlloc", column = "timeAlloc"),
            @Result(property = "timeReturned", column = "timeReturned"),
            @Result(property = "returned", column = "returned")
    })
    InventoryAlloc getById(long InventoryAlloc);

    @Select(getByItem)
    @Results(value = {
            @Result(property = "idInvAlloc", column = "idInvAlloc"),
            @Result(property = "idStaff", column = "idStaff"),
            @Result(property = "idItem", column = "idItem"),
            @Result(property = "timeAlloc", column = "timeAlloc"),
            @Result(property = "timeReturned", column = "timeReturned"),
            @Result(property = "returned", column = "returned")
    })
    InventoryAlloc getByItem(long idItem);

    @Select(getAllByStaff)
    List<InventoryAlloc> getAllByStaff(long idStaff);

    @Insert(insert)
    @Options(useGeneratedKeys = true, keyProperty = "idInvAlloc")
    void insert(InventoryAlloc alloc);
}
