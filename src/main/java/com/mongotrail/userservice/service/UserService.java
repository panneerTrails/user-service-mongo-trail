package com.mongotrail.userservice.service;

import com.mongotrail.userservice.entity.User;
import com.mongotrail.userservice.entity.UserEmailOnly;
import com.mongotrail.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public String createUser(User user) {
        LOGGER.info("UserService::Inside addUser:::");
        userRepository.save(user);
        return "success";
    }

    public User getById(String id) {
        LOGGER.info("UserService::Inside getById:::");
        return userRepository.findOne(id);
    }

    public Boolean isUserExists(String id) {
        LOGGER.info("UserService::Inside isUserExists:::");
        return userRepository.exists(id);
    }

    public String deleteUser(String id) {
        LOGGER.info("UserService::Inside deleteUser:::");
        userRepository.delete(id);
        return "success";
    }

    public String removeUser(String name) {
        LOGGER.info("UserService::Inside deleteUser:::");
        return userRepository.removeUser(name);
    }

    //Custom Query - Starts
    public String updateUser(User user, String id) {
        LOGGER.info("UserService::Inside updateUser:::");
        userRepository.updateUserByName(user, id);
        return "success";
    }

    public String updateFirst(String findName, String replaceName) {
        LOGGER.info("UserService::Inside updateUser:::");
        userRepository.updateFirst(findName, replaceName);
        return "success";
    }

    public String updateMulti(String findName, String replaceName) {
        LOGGER.info("UserService::Inside updateUser:::");
        userRepository.updateMulti(findName, replaceName);
        return "success";
    }

    public User findAndModify(String findName, String replaceName) {
        LOGGER.info("UserService::Inside updateUser:::");
        return userRepository.findAndModify(findName, replaceName);
    }

    public User upsert(String findName, String replaceName) {
        LOGGER.info("UserService::Inside updateUser:::");
        return userRepository.upsert(findName, replaceName);
    }



    public List<User> getUserByName(String name) {
        LOGGER.info("UserService::Inside getByName:::");
        return userRepository.getUserByName(name);
    }

    public List<User> findAllUser(String name) {
        LOGGER.info("UserService::Inside findAllUser:::");
        return userRepository.findAll(new Sort(Sort.Direction.ASC, name));
    }

    public List<User> getUserByIsLike(String name) {
        LOGGER.info("UserService::Inside getUserByIsLike:::");
        return userRepository.getUserByIsLike(name);
    }

    //Custom Query - Ends

    public String updateUserPatchMapping(UserEmailOnly user, String id){
        User availableUserInfo = userRepository.findOne(id);
        LOGGER.info("UserService::Inside updateUserPatchMapping:::userInfo::", availableUserInfo);
        if (!availableUserInfo.getEmail().equals(user.getEmail())){
            availableUserInfo.setEmail(user.getEmail());
        }
        userRepository.save(availableUserInfo);
        return "success";
    }

}
