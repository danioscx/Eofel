package com.vass.api.model;


public class Region extends Model {

    String id;
    String name;

    public Region(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }


}
