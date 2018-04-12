package com.mongotrail.userservice.controller;

import com.mongotrail.userservice.entity.User;
import com.mongotrail.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/UserService")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        LOGGER.info("Inside UserController::addUser:::");
        userService.addUser(user);
        return "success";
    }

    @GetMapping(value = "/{id}")
    public User getById(@PathVariable String id) {
        LOGGER.info("Inside UserController::getById:::");
        return userService.getById(id);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable String id) {
        LOGGER.info("Inside UserController::deleteUser:::");
        userService.deleteUser(id);
        return "success";
    }

    //Custom Query - Starts
    @PutMapping("/updateUser")
    public String updateUserByPutMapping(@RequestBody User user) {
        LOGGER.info("Inside UserController::updateUserByPutMapping:::");
        userService.updateUser(user);
        return "success";
    }


    @GetMapping(value = "/getUserByName/{name}")
    public List<User> getUserByName(@PathVariable String name) {
        LOGGER.info("Inside UserController::getUserByName:::");
        return userService.getUserByName(name);
    }

    @GetMapping(value = "/getUserByIsLike/{name}")
    public List<User> getUserByIsLike(@PathVariable String name) {
        LOGGER.info("Inside UserController::getUserByName:::");
        return userService.getUserByIsLike(name);
    }
    //Custom Query - Ends


}
