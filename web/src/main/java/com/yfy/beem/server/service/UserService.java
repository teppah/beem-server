package com.yfy.beem.server.service;

// TODO: write javadoc

import com.yfy.beem.server.datamodel.User;

import java.util.List;

/**
 * Abstraction layer for accessing the database
 * // TODO: write javadoc for each method
 * */
public interface UserService {
    List<User> getUsersList();
    User addUser(User user);
    void deleteUserByUUID(long id);
    User retrieveUserByUUID(long id);
    List<User> retrieveUsersMatchingName(String name);


}
