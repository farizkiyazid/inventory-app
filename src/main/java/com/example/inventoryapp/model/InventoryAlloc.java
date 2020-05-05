package com.example.inventoryapp.model;

import java.sql.Timestamp;

public class InventoryAlloc {
    private long idInvAlloc;
    private long idStaff;
    private long idItem;
    private Timestamp timeAlloc;
    private boolean returned;

    public InventoryAlloc(long idInvAlloc, long idStaff, long idItem, Timestamp timeAlloc, boolean returned) {
        super();
        this.idInvAlloc = idInvAlloc;
        this.idStaff = idStaff;
        this.idItem = idItem;
        this.timeAlloc = timeAlloc;
        this.returned = returned;
    }

    public InventoryAlloc(long idStaff, long idItem){
        this(0,idStaff,idItem,new Timestamp(0), false);
    }

    public InventoryAlloc(){
    }

    public long getIdInvAlloc() {
        return idInvAlloc;
    }

    public void setIdInvAlloc(long idInvAlloc) {
        this.idInvAlloc = idInvAlloc;
    }

    public long getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(long idStaff) {
        this.idStaff = idStaff;
    }

    public long getIdItem() {
        return idItem;
    }

    public void setIdItem(long idItem) {
        this.idItem = idItem;
    }

    public Timestamp getTimeAlloc() {
        return timeAlloc;
    }

    public void setTimeAlloc(Timestamp timeAlloc) {
        this.timeAlloc = timeAlloc;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}
