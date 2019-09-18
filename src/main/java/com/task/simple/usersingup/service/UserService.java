package com.task.simple.usersingup.service;

import com.task.simple.usersingup.model.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(final UserDTO userDTO);

    List<UserDTO> getAll();

    UserDTO getUser(final String email);
}
