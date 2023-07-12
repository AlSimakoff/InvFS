package org.example.services;

public class StoreFull {
    private int id;
    private String name;
    private String ser;
    private String inv;
    private String Action;
    private String note;

    public StoreFull(int id, String name, String ser, String inv, String Action, String note){
        this.id=id;
        this.name=name;
        this.ser=ser;
        this.inv=inv;
        this.Action=Action;
        this.note=note;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSer() {
        return ser;
    }

    public void setSer(String ser) {
        this.ser = ser;
    }

    public String getInv() {
        return inv;
    }

    public void setInv(String inv) {
        this.inv = inv;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
