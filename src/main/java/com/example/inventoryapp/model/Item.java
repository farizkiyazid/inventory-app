package com.example.inventoryapp.model;

import org.json.simple.JSONObject;

public class Item {
    private long idItem;
    private String itemType;
    private boolean available;

    public Item(long idItem, String name, boolean available) {
        super();
        this.idItem = idItem;
        this.itemType = name;
        this.available = available;
    }

    public Item(String itemType, boolean available){
        this(0,itemType, available);
    }

    public Item(){
    }

    public JSONObject toJsonObject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idItem", idItem);
        jsonObject.put("itemType", itemType);
        jsonObject.put("available", available);
        return  jsonObject;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public long getIdItem() {
        return idItem;
    }

    public void setIdItem(long idItem) {
        this.idItem = idItem;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
