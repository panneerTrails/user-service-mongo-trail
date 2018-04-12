package com.mongotrail.userservice.repository;

import com.mongotrail.userservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String>, UserRepositoryCustom{

    @Query("{ 'name' : ?0 }")
    List<User> getUserByName(String name);

    @Query(value = "{'name': {$regex : ?0, $options: 'i'}}")
    List<User> getUserByIsLike(String name);

}
