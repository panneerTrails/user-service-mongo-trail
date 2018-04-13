package com.mongotrail.userservice.repository;

import com.mongodb.WriteResult;
import com.mongotrail.userservice.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;


@Service
public class UserRepositoryImpl implements UserRepositoryCustom{

    @Autowired
    MongoTemplate mongoTemplate;

    public User updateUserByName(User user, String id){

        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();

        String name = user.getName();
        if (StringUtils.isBlank(name)){
            update.set("name", name);
        }

        Integer age = user.getAge();
        if (age!=null){
            update.set("age", age);
        }

        String email = user.getEmail();
        if (StringUtils.isBlank(email)){
            update.set("email", email);
        }

        WriteResult result = mongoTemplate.updateFirst(query, update, User.class);
        User userUpdated = null;
        if (result != null && result.getN() == 1)
            userUpdated = mongoTemplate.findOne(query, User.class);

        return userUpdated;
    }

    public User updateFirst(String findName, String replaceName){

        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(findName));
        Update update = new Update();
        update.set("name", replaceName);
        WriteResult result = mongoTemplate.updateFirst(query, update, User.class);

        User userUpdated = null;
        if (result != null && result.getN() == 1)
            userUpdated = mongoTemplate.findOne(query, User.class);

        return userUpdated;

    }

    public User updateMulti(String findName, String replaceName){

        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(findName));
        Update update = new Update();
        update.set("name", replaceName);
        WriteResult result = mongoTemplate.updateMulti(query, update, User.class);

        User userUpdated = null;
        if (result != null && result.getN() == 1)
            userUpdated = mongoTemplate.findOne(query, User.class);

        return userUpdated;

    }

    public User findAndModify(String findName, String replaceName){

        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(findName));
        Update update = new Update();
        update.set("name", replaceName);
        return mongoTemplate.findAndModify(query, update, User.class);

    }

    public User upsert(String findName, String replaceName){

        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(findName));
        Update update = new Update();
        update.set("name", replaceName);
        WriteResult result = mongoTemplate.upsert(query, update, User.class);

        User userUpdated = null;
        if (result != null && result.getN() == 1)
            userUpdated = mongoTemplate.findOne(query, User.class);

        return userUpdated;

    }

    public String removeUser(String removeName){
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(removeName));
        User removeUser = mongoTemplate.findOne(query, User.class);
        WriteResult result = mongoTemplate.remove(removeUser);
        return "Removed User";
    }
}
