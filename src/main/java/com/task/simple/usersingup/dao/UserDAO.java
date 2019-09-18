package com.task.simple.usersingup.dao;

import com.task.simple.usersingup.exceptions.UserDoesNotExists;
import com.task.simple.usersingup.model.User;
import com.task.simple.usersingup.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserDAO implements BasicDAO {

    private final MongoTemplate mongo;

    @Autowired
    public UserDAO(final MongoTemplate mongoTemplate) {
        mongo = mongoTemplate;
    }

    @Override
    public UserDTO findUserByEmail(final String email) {
        return Optional.ofNullable(mongo.findOne(new Query(Criteria.where("email").is(email)), User.class))
                .map(UserDTO::ofUser)
                .orElseThrow(UserDoesNotExists::new);
    }

    @Override
    public boolean userWithEmailExists(final String email) {
        return mongo.exists(new Query(Criteria.where("email").is(email)), User.class);
    }

    @Override
    public UserDTO insertUser(final UserDTO userDTO) {
        return UserDTO.ofUser(mongo.insert(userDTO.toUser()));
    }

    @Override
    public List<UserDTO> getAll() {
        return mongo.findAll(User.class).stream()
                .map(UserDTO::ofUser)
                .collect(Collectors.toList());
    }
}
