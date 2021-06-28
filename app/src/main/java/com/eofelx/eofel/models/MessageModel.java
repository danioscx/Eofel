package com.eofelx.eofel.models;

public class MessageModel extends BaseModel {

    public MessageModel(int id, String name, String messages) {
        this.id = id;
        this.name = name;
        this.messages = messages;
    }

    private int id;
    private String name;
    private String imageUrl;
    private String messages;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }
}
