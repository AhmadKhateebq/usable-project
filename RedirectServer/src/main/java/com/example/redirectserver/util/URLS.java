package com.example.redirectserver.util;

public interface URLS {
    String BASE_AUTH_URL = "http://localhost:8085";
    String GET_TOKEN = BASE_AUTH_URL + "/getToken";
    String IS_USER_VALID = BASE_AUTH_URL + "/validate";
    String IS_TOKEN_VALID = BASE_AUTH_URL + "/isValid";
    String GET_CLAIMS = BASE_AUTH_URL + "/claims";

    String BASE_SERVER_URL = "82.102.214.82:8000";
    String CATEGORY_URL = BASE_SERVER_URL+"/api/category";
    String ORDER_URL = BASE_SERVER_URL+"/api/order";
    String PRODUCT_URL = BASE_SERVER_URL+"/api/product";
}
