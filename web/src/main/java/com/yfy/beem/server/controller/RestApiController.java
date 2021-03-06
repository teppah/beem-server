package com.yfy.beem.server.controller;

import com.yfy.beem.server.datamodel.User;
import com.yfy.beem.server.respository.UserRepository;
import com.yfy.beem.server.util.ApiMappings;
import com.yfy.beem.server.util.RequestParamMappings;
import com.yfy.beem.server.util.WebMappings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


/**
 * Main controller for the API endpoints.
 */
@RestController
@Slf4j
public class RestApiController {
    private UserRepository userRepository;

    @Autowired
    public RestApiController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(ApiMappings.ADD_USER)
    public void registerUser(@RequestParam(RequestParamMappings.ID) long id,
                             @RequestParam(RequestParamMappings.NAME) String name,
                             @RequestParam(RequestParamMappings.PUBLIC_KEY) String publicKey,
                             HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        // remove all superfluous spaces, beginning tag and end tag of RSA public key
//        publicKey = publicKey.replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "").replaceAll(" ","");
        log.info("httpServletRequest = {}", request);
        log.info("publicKey = {}", publicKey);
        User user = new User(id, name, request.getRemoteAddr(), publicKey);
        userRepository.save(user);
        log.info("Successfully saved new user: {}", user);
        response.sendRedirect(WebMappings.GET_USERS);
    }

    @GetMapping(ApiMappings.GET_USERS)
    public User[] getActiveUsers(@RequestParam(value = RequestParamMappings.ID, required = false) Long id,
                                 @RequestParam(value = RequestParamMappings.NAME, required = false) String name) {
        List<User> users = StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        // filtering logic
        // filter by id
        if (id != null) {
            users.removeIf(user -> user.getId() != id);
        }
        // filter by name
        if (name != null) {
            users.removeIf(user -> !user.getName().equalsIgnoreCase(name));
        }

        User[] userArray = new User[users.size()];
        userArray = users.toArray(userArray);
        log.info("existing users = {}", users);
        return userArray;
    }

    @GetMapping(ApiMappings.DELETE_USER + "/{id}")
    public void deleteUser(@PathVariable("id") Long id,
                           HttpServletResponse response) throws IOException {
        log.info("attempting to delete user with id = {}", id);
        userRepository.deleteById(id);
        response.sendRedirect(WebMappings.GET_USERS);
    }

//    @PostMapping(ApiMappings.DELETE_USER + "/{id}")
//    public void deleteUser(@PathVariable("{id}") Long id,
//                           HttpServletResponse response) throws IOException {
//        log.info("attempting to delete user with id = {}", id);
//        userRepository.deleteById(id);
//        response.sendRedirect(WebMappings.GET_USERS);
//    }

}
