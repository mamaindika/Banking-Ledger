package com.nimidev.bankingLedger.controller;

import com.nimidev.bankingLedger.entity.User;
import com.nimidev.bankingLedger.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "Create user")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public User createUser (@RequestBody User user) {
        return userService.createUser(user);
    }

    @ApiOperation(value = "Read user")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User readUser (@PathVariable("id") String id) {
        return userService.readUser(id);
    }
}
