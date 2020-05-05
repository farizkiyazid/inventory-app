package com.example.inventoryapp.model;

import org.json.simple.JSONObject;

public class Staff {
    private long idStaff;
    private String name;
    private  String division;

    public Staff(long idStaff, String name, String division) {
        super();
        this.idStaff = idStaff;
        this.name = name;
        this.division = division;
    }

    public Staff(String name, String division){
        this(0,name, division);
    }

    public Staff(){
    }

    public JSONObject toJsonObject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idStaff", idStaff);
        jsonObject.put("name", name);
        jsonObject.put("division", division);
        return  jsonObject;
    }

    public long getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(long idStaff) {
        this.idStaff = idStaff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

}
