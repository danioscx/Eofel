package com.eofelx.eofel.models;

public class WalletModel extends BaseModel {

    private int id;
    private String date;
    private String value;
    private String status;


    public WalletModel() { // default constructor

    }
    public WalletModel(String date, String value, String status) {
        this.date = date;
        this.value = value;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
