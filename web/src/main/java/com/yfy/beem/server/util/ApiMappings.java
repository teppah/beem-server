package com.yfy.beem.server.util;


/**
 * Centralized request mapping constants
 * */
public class ApiMappings {
    // do not let anybody instantiate this class
    private ApiMappings() {}

    // ----- MAPPINGS ------
    public static final String API_PREFIX = "api/";
    public static final String ADD_USER = API_PREFIX + "add-user";
    public static final String GET_USERS = API_PREFIX + "users";
}
