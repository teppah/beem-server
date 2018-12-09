package com.yfy.beem.server.controller;

import com.yfy.beem.server.util.ViewNames;
import com.yfy.beem.server.util.WebRequestMappings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller that dispatches the appropriate templates for displaying register user forms, listing active users, etc
 */
@Controller
public class WebController {
    @GetMapping(WebRequestMappings.ADD_USER)
    public String addUser() {
        return ViewNames.ADD_USER;
    }
}
