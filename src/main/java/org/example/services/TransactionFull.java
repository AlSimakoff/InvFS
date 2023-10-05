package org.example.services;

import java.util.Date;

public class TransactionFull {
    private int id;
    private String name;
    private String ser;
    private String inv;
    private String from_take;
    private String to_delivery;
    private String date_delivery;
    private String note;

    public TransactionFull(int id, String name, String ser,
                           String inv, String from_take,
                           String to_delivery,
                           String date_delivery, String note) {
        this.id = id;
        this.name = name;
        this.ser = ser;
        this.inv = inv;
        this.from_take = from_take;
        this.to_delivery = to_delivery;
        this.date_delivery = date_delivery;
        this.note = note;
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

    public String getFrom_take() {
        return from_take;
    }

    public void setFrom_take(String from_take) {
        this.from_take = from_take;
    }

    public String getTo_delivery() {
        return to_delivery;
    }

    public void setTo_delivery(String to_delivery) {
        this.to_delivery = to_delivery;
    }

    public String getDate_delivery() {
        return date_delivery;
    }

    public void setDate_delivery(String date_delivery) {
        this.date_delivery = date_delivery;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

