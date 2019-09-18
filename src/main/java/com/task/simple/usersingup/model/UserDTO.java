package com.task.simple.usersingup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String city;
    private String street;
    private Integer houseNumber;

    public static UserDTO ofUser(final User user) {
        return new UserDTO(
                user.getPersonalInfo().getName(),
                user.getPersonalInfo().getSurname(),
                user.getEmail(),
                user.getPassword(),
                user.getAddress().getCity(),
                user.getAddress().getStreet(),
                user.getAddress().getHouseNumber()
        );
    }

    public User toUser() {
        final User user = new User();
        user.setEmail(email);
        user.setPersonalInfo(mapPersonalInfo(firstName, lastName));
        user.setCreated(new Date());
        user.setPassword(password);
        user.setAddress(mapAddress(city, street, houseNumber));
        return user;
    }

    private static PersonalInfo mapPersonalInfo(final String firstName, final String lastName) {
        return allNull(firstName, lastName) ? PersonalInfo.EMPTY : new PersonalInfo(firstName, lastName);
    }

    private static Address mapAddress(final String city, final String street, final Integer houseNumber) {
        return allNull(city, street, houseNumber) ? Address.EMPTY : new Address(city, street, houseNumber);
    }

    private static boolean allNull(Object... objects) {
        return Stream.of(objects).allMatch(Objects::isNull);
    }
}
