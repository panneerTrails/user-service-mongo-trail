package com.mongotrail.userservice.service;

import com.mongotrail.userservice.entity.User;
import com.mongotrail.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public String addUser(User user) {
        LOGGER.info("UserService::Inside addUser:::");
        userRepository.save(user);
        return "success";
    }

    public User getById(String id) {
        LOGGER.info("UserService::Inside getById:::");
        return userRepository.findOne(id);
    }

    public String deleteUser(String id) {
        LOGGER.info("UserService::Inside deleteUser:::");
        userRepository.delete(id);
        return "success";
    }

    //Custom Query - Starts
    public String updateUser(User user) {
        LOGGER.info("UserService::Inside updateUser:::");
        userRepository.updateUserByName(user);
        return "success";
    }

    public List<User> getUserByName(String name) {
        LOGGER.info("UserService::Inside getByName:::");
        return userRepository.getUserByName(name);
    }


    public List<User> getUserByIsLike(String name) {
        LOGGER.info("UserService::Inside getUserByIsLike:::");
        return userRepository.getUserByIsLike(name);
    }

    //Custom Query - Ends



}
