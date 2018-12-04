package com.yfy.beem.server.controller;

import com.yfy.beem.server.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RestApiController {
    private UserRepository userRepository;

    @Autowired
    public RestApiController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("test")
    public String test() {
        log.info("userRepository = {}", userRepository);
        return "test";
    }
}
