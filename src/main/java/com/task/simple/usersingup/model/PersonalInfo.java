package com.task.simple.usersingup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfo {
    private String name;
    private String surname;

    @Transient
    public static final PersonalInfo EMPTY = new PersonalInfo();
}
