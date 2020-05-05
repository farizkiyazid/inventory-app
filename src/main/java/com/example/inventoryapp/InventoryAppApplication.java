package com.example.inventoryapp;

import com.example.inventoryapp.model.Item;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MappedTypes(Item.class)
@MapperScan("com.example.inventoryapp.mapper")
@SpringBootApplication
public class InventoryAppApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(InventoryAppApplication.class, args);
    }

}
