package com.yfy.beem.server.service;

import com.yfy.beem.server.datamodel.User;
import com.yfy.beem.server.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Implementation of UserService that uses Hibernate to access a cloud database.
 * TODO: write javadoc
 */
@Service
@Slf4j
public class HibernateBasedUserService implements UserService {
    private UserRepository userRepository;

    @Autowired
    public HibernateBasedUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        log.info("UserService instantiated, userRepository = {}", userRepository);
    }

    @Override
    public List<User> getUsersList() {
        log.info("request for a list of active users");
        return StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public User addUser(User user) {
        log.info("attempting to save user = {}", user);
        return userRepository.save(user);
    }

    @Override
    public void deleteUserByUUID(long id) {
        log.info("attempting to delete user with id = {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public User retrieveUserByUUID(long id) {
        log.info("attempting to retrieve user by id = {}", id);
        Optional<User> result = userRepository.findById(id);
        return result.orElse(null);
    }

    @Override
    public List<User> retrieveUsersMatchingName(String name) {
        log.info("attempting to retrieve users that have the matching name = {}", name);
        return StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .collect(Collectors.toUnmodifiableList());
    }
}
