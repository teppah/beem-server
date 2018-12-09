package com.yfy.beem.server.util;

/**
 * Names of view templates to display to user
 * */
public class ViewNames {
    // do not let anyone instantiate
    private ViewNames() {}

    // ------ CONSTANTS ------
    public static final String ADD_USER = "add_user";
    public static final String LIST_USERS = "users";
    public static final String REDIRECT_LIST_USERS = "redirect:/" + LIST_USERS;
}
