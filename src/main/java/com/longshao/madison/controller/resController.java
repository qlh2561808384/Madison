package com.longshao.madison.controller;

import com.longshao.madison.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class resController {
    @Autowired
    private UserInfo userInfo;
    @RequestMapping(value = "res",method = RequestMethod.POST)
    public UserInfo res(HttpServletRequest request) {
        userInfo = new UserInfo();
        return userInfo;
    }
}
