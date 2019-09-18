package com.task.simple.usersingup.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    @NonNull
    private String email;

    @NonNull
    private String password;
    private PersonalInfo personalInfo;
    private Address address;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date created;
}
