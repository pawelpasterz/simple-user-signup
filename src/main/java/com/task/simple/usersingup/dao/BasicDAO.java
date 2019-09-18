package com.task.simple.usersingup.dao;

import com.task.simple.usersingup.model.UserDTO;

import java.util.List;

public interface BasicDAO {
    UserDTO findUserByEmail(final String email);

    boolean userWithEmailExists(final String email);

    UserDTO insertUser(final UserDTO userDTO);

    List<UserDTO> getAll();
}
