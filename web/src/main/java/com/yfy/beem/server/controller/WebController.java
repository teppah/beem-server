package com.yfy.beem.server.controller;

import com.yfy.beem.server.datamodel.User;
import com.yfy.beem.server.respository.UserRepository;
import com.yfy.beem.server.util.ModelAttributes;
import com.yfy.beem.server.util.ViewNames;
import com.yfy.beem.server.util.WebRequestMappings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller that dispatches the appropriate templates for displaying register user forms, listing active users, etc
 */
@Controller
@Slf4j
public class WebController {
    private UserRepository userRepository;

    @Autowired
    public WebController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(WebRequestMappings.ADD_USER)
    public String addUser() {
        return ViewNames.ADD_USER;
    }

    @GetMapping(WebRequestMappings.LIST_USERS)
    public String listUsers(Model model) {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(userList::add);
        User[] userArr = userList.toArray(new User[0]);
        log.info("userArr = {}", userArr);
        model.addAttribute(ModelAttributes.USERS, userArr);
        return ViewNames.LIST_USERS;
    }

}
