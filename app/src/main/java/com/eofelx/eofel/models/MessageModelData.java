package com.eofelx.eofel.models;

public class MessageModelData extends BaseModel {

    int id;
    int type;
    String date;
    String message;

    public MessageModelData(int type, String date, String message) {
        this.type = type;
        this.date = date;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
