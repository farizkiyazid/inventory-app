package com.example.inventoryapp.mapper;

import com.example.inventoryapp.model.Staff;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StaffMapper {
    /**
     * Karena beda microservice, kita hanya bisa read only dari tabel staff.
     * (Tidak ada Insert/update/delete)
     */
    final String getAll = "SELECT * FROM staffs";
    final String getById = "SELECT * FROM staffs WHERE idStaff = #{idStaff}";


    @Select(getAll)
    List<Staff> getAll();

    @Select(getById)
    @Results(value = {
            @Result(property = "idStaff", column = "idStaff"),
            @Result(property = "name", column = "name"),
            @Result(property = "division", column = "division")
    })
    Staff getById(long idStaff);
}
