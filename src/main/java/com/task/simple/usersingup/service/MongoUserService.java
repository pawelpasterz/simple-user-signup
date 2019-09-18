package com.task.simple.usersingup.service;

import com.task.simple.usersingup.dao.UserDAO;
import com.task.simple.usersingup.exceptions.UserExistsException;
import com.task.simple.usersingup.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoUserService implements UserService {

    private final UserDAO dao;

    @Autowired
    public MongoUserService(final UserDAO userDAO) {
        this.dao = userDAO;
    }

    @Override
    public UserDTO createUser(final UserDTO userDTO) {
        if (!dao.userWithEmailExists(userDTO.getEmail())) {
            return dao.insertUser(userDTO);
        } else {
            throw new UserExistsException();
        }
    }

    @Override
    public List<UserDTO> getAll() {
        return dao.getAll();
    }

    @Override
    public UserDTO getUser(final String email) {
        return dao.findUserByEmail(email);
    }
}
