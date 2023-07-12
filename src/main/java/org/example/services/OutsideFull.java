package org.example.services;

public class OutsideFull {
    private int id;
    private String name;
    private String ser;
    private String inv;
    private String delv;
    private String take;
    private String Action;
    private String note;

    public OutsideFull(int id, String name, String ser, String inv, String delv, String take, String Action, String note){
        this.setId(id);
        this.setName(name);
        this.setSer(ser);
        this.setInv(inv);
        this.setDelv(delv);
        this.setTake(take);
        this.setAction(Action);
        this.setNote(note);
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

    public String getDelv() {
        return delv;
    }

    public void setDelv(String delv) {
        this.delv = delv;
    }

    public String getTake() {
        return take;
    }

    public void setTake(String take) {
        this.take = take;
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
