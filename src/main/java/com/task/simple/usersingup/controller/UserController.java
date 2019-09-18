package com.task.simple.usersingup.controller;

import com.task.simple.usersingup.model.UserDTO;
import com.task.simple.usersingup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestParam final String email,
                              @RequestParam final String password,
                              @RequestParam(required = false) final String name,
                              @RequestParam(required = false) final String surname,
                              @RequestParam(required = false) final String city,
                              @RequestParam(required = false) final String street,
                              @RequestParam(required = false) final Integer house) {
        return service.createUser(new UserDTO(name, surname, email, password, city, street, house));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO createUserWithBody(@RequestBody final UserDTO userDTO) {
        return service.createUser(userDTO);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers() {
        return service.getAll();
    }

    @GetMapping("/{email}")
    public UserDTO getUser(@PathVariable final String email) {
        return service.getUser(email);
    }
}
