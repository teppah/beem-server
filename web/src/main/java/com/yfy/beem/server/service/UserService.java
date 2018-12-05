package com.yfy.beem.server.service;

// TODO: write javadoc

import com.yfy.beem.server.datamodel.User;

import java.util.List;

/**
 * Abstraction layer for accessing the database
 * */
public interface UserService {
    List<User> getActiveUsers();
    boolean addUser(User user);
    User retrieveUserByUUID(long id);
    List<User> retrieveUsersMatchingName(String name);


}
