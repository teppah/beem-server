package com.yfy.beem.server.controller;

import com.yfy.beem.server.datamodel.User;
import com.yfy.beem.server.respository.UserRepository;
import com.yfy.beem.server.util.ApiMappings;
import com.yfy.beem.server.util.RequestParamMappings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@Slf4j
public class RestApiController {
    private UserRepository userRepository;

    @Autowired
    public RestApiController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(ApiMappings.REGISTER_USER)
    public String registerUser(@RequestParam(RequestParamMappings.ID) long id,
                               @RequestParam(RequestParamMappings.NAME) String name,
                               @RequestParam(RequestParamMappings.PUBLIC_KEY) String publicKey,
                               HttpServletRequest request) {
        // remove all superfluous spaces, beginning tag and end tag of RSA public key
//        publicKey = publicKey.replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "").replaceAll(" ","");
        log.info("httpServletRequest = {}", request);
        log.info("publicKey = {}", publicKey);
        User user = new User(id, name, request.getRemoteAddr(), publicKey);
        userRepository.save(user);
        log.info("Successfully saved new user: {}", user);
        return user.toString();
    }

    @GetMapping(ApiMappings.GET_USERS)
    public User[] getActiveUsers() {
        List<User> users = StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        User[] userArray = new User[users.size()];
        userArray = users.toArray(userArray);
        return userArray;
    }
}
