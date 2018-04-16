package com.mongotrail.userservice.repository;

import com.mongotrail.userservice.entity.User;

public interface UserRepositoryCustom {

    public User updateUserByName(User user, String id);

    public User updateFirst(String findName, String replaceName);

    public User updateMulti(String findName, String replaceName);

    public User findAndModify(String findName, String replaceName);

    public User upsert(String findName, String replaceName);

    public String removeUser(String removeName);

}
