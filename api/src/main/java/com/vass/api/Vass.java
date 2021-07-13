package com.vass.api;


public class Vass {

    public Vass() {
    }

    private static final String prefix_url = "/api/v1";

    public static final String APP_URL = "http://192.168.1.40:5000";
    public static final String ALL_PROVINCES = APP_URL + "/api/v1/get/provinces/";
    public static final String REGENCIES = APP_URL + "/api/v1/get/regencies/by/";
    public static final String DISTRICT = APP_URL + "/api/v1/get/districts/by/";
    public static final String VILLAGES = APP_URL + "/api/v1/get/villages/by/";


    public static final String GET_PHONE_NUMBER = APP_URL + prefix_url + "/app/check/phone";
    public static final String GET_EMAIL_ADDRESS = APP_URL + prefix_url +  "/app/check/email";
    public static final String CREATE_USER = APP_URL + prefix_url +  "/app/create/user";
    public static final String CREATE_MERCHANT = APP_URL + prefix_url +  "/user/create/merchant";
    public static final String CREATE_MERCHANT_INFO = APP_URL + prefix_url +  "/user/set/merchant/info";
    public static final String CREATE_OPEN_STATUS = APP_URL + prefix_url + "/user/set/open/status";


}
