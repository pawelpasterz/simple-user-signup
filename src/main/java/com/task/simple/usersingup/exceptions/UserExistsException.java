package com.task.simple.usersingup.exceptions;

public class UserExistsException extends AppException {

    public UserExistsException() {
        super("User with this email exists in data base");
    }
}
