package com.task.simple.usersingup.exceptions;

public class UserDoesNotExists extends AppException {

    public UserDoesNotExists() {
        super("User does not exists in data base");
    }
}
