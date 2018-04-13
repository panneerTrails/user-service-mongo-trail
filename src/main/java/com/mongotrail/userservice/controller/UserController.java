package com.mongotrail.userservice.controller;

import com.mongotrail.userservice.entity.User;
import com.mongotrail.userservice.entity.UserEmailOnly;
import com.mongotrail.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/UserService")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestBody User user) {
        LOGGER.info("Inside UserController::addUser:::");
        userService.createUser(user);
        return "success";
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getById(@PathVariable String id) {
        LOGGER.info("Inside UserController::getById:::");
        return userService.getById(id);
    }

    @PatchMapping("/updateUser/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateUserByPatchMapping(@RequestBody UserEmailOnly user, @PathVariable String id) {
        LOGGER.info("Inside UserController::updateUserByPutMapping:::");
        userService.updateUserPatchMapping(user, id);
        return "success";
    }

    @DeleteMapping("/deleteUser/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@PathVariable String id) {
        LOGGER.info("Inside UserController::deleteUser:::");
        userService.deleteUser(id);
        return "success";
    }

    //Custom Query - Starts
    @PutMapping("/updateUser/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateUserByPutMapping(@RequestBody User user,@PathVariable String id) {
        LOGGER.info("Inside UserController::updateUserByPutMapping:::");
        userService.updateUser(user, id);
        return "success";
    }

    @PutMapping("/updateFirst/{findName}/{replaceName}")
    @ResponseStatus(HttpStatus.OK)
    public String updateFirst(@PathVariable String findName, @PathVariable String replaceName) {
        LOGGER.info("Inside UserController::updateFirst:::");
        userService.updateFirst(findName, replaceName);
        return "success";
    }

    @PutMapping("/updateMulti/{findName}/{replaceName}")
    @ResponseStatus(HttpStatus.OK)
    public String updateMulti(@PathVariable String findName, @PathVariable String replaceName) {
        LOGGER.info("Inside UserController::updateFirst:::");
        userService.updateMulti(findName, replaceName);
        return "success";
    }

    @PutMapping("/findAndModify/{findName}/{replaceName}")
    @ResponseStatus(HttpStatus.OK)
    public User findAndModify(@PathVariable String findName, @PathVariable String replaceName) {
        LOGGER.info("Inside UserController::updateFirst:::");
        return userService.findAndModify(findName, replaceName);
    }

    @PutMapping("/upsert/{findName}/{replaceName}")
    @ResponseStatus(HttpStatus.OK)
    public User upsert(@PathVariable String findName, @PathVariable String replaceName) {
        LOGGER.info("Inside UserController::updateFirst:::");
        return userService.upsert(findName, replaceName);
    }

    @GetMapping(value = "/getUserByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUserByName(@PathVariable String name) {
        LOGGER.info("Inside UserController::getUserByName:::");
        return userService.getUserByName(name);
    }

    @GetMapping(value = "/getUserByIsLike/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUserByIsLike(@PathVariable String name) {
        LOGGER.info("Inside UserController::getUserByName:::");
        return userService.getUserByIsLike(name);
    }

    @GetMapping(value = "/isUserExists/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean isUserExists(@PathVariable String id) {
        LOGGER.info("Inside UserController::isUserExists:::");
        return userService.isUserExists(id);
    }

    @DeleteMapping("/removeUser/{name}")
    @ResponseStatus(HttpStatus.OK)
    public String removeUser(@PathVariable String name) {
        LOGGER.info("Inside UserController::deleteUser:::");
        return userService.removeUser(name);
    }

    @GetMapping(value = "/findAll/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAllUser(@PathVariable String name) {
        LOGGER.info("Inside UserController::findAllUser:::");
        return userService.findAllUser(name);
    }
    //Custom Query - Ends

    @RequestMapping(value = "/{id}",method = RequestMethod.HEAD)
    @ResponseBody @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getByIdHead(@PathVariable String id) {
        LOGGER.info("Inside UserController::getById:::");
        User user = userService.getById(id);
        Map<String, String> source = new HashMap<>();
        source.put("now", Calendar.getInstance().getTime().toString());
        return source;
    }

    @OPTIONS
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getOptions")
    public Response optionsForUserResource() {
        return Response.status(200)
                .header("Allow","POST, PUT, GET")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .header("Content-Length", "0")
                .build();
    }



}
