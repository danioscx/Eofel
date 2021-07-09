package com.vass.api;


import java.util.Map;


public interface Request<T> {

    public void setHeaders(Map<String, String> stringMap);
}
