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


@Service
public class UserRepositoryImpl implements UserRepositoryCustom{

    @Autowired
    MongoTemplate mongoTemplate;

    public User updateUserByName(User user){

        Query query = new Query(Criteria.where("_id").is(user.getId()));
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

}
