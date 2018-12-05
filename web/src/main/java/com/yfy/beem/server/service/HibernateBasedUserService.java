package com.yfy.beem.server.service;

import com.yfy.beem.server.datamodel.User;
import com.yfy.beem.server.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Implementation of UserService that uses Hibernate to access a cloud database.
 * TODO: write javadoc
 * */
@Service
@Slf4j
public class HibernateBasedUserService implements UserService{
    private UserRepository userRepository;

    @Autowired
    public HibernateBasedUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getActiveUsers() {
        return StreamSupport
                .stream(userRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserByUUID(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User retrieveUserByUUID(long id) {
        Optional<User> result = userRepository.findById(id);
        return result.orElse(null);
    }

    @Override
    public List<User> retrieveUsersMatchingName(String name) {
        List<User> matchingUsers = StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
        return matchingUsers;
    }
}
