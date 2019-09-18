package com.task.simple.usersingup;

import com.task.simple.usersingup.model.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserSingupApplicationTests {

    private static final Random random = new Random();
    private static final String URL = "http://localhost:";
    private static final String CONTROLLER_SUFFIX = "/user";

    private static String CREATE_USER_PATH;
    private static String GET_ALL_PATH;
    private static String GET_BY_EMAIL;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() {
        final String serverUrl = URL + port +CONTROLLER_SUFFIX;
        CREATE_USER_PATH = serverUrl + "/create";
        GET_BY_EMAIL = serverUrl + "/";
        GET_ALL_PATH = serverUrl + "/all";
    }

    @Test
    public void shouldThrowAnErrorIfEmailIsMissing() {
        final UserDTO userDTO = new UserDTO("anyFirstName", "anyLastName", null, "anyPassword", "anyCity", "anyStreet", random.nextInt());
        assertThrows(RestClientException.class, () -> template.postForEntity(CREATE_USER_PATH, userDTO, UserDTO.class));
    }

    @Test
    public void shouldThrowAnErrorIfPasswordIsMissing() {
        final UserDTO userDTO = new UserDTO("anyFirstName", "anyLastName", "any@email", null, "anyCity", "anyStreet", random.nextInt());
        assertThrows(RestClientException.class, () -> template.postForEntity(CREATE_USER_PATH, userDTO, UserDTO.class));
    }

    @Test
    public void shouldCreateAndReturnNewlyCreatedUser() {
        final UserDTO userDTO = new UserDTO("anyFirstName", "anyLastName", "any@email", "anyPassword", "anyCity", "anyStreet", random.nextInt());
        final ResponseEntity response = template.postForEntity(CREATE_USER_PATH, userDTO, UserDTO.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(response.getBody(), userDTO);

        final ResponseEntity fetchedEntity = template.getForEntity(GET_BY_EMAIL + "/" + userDTO.getEmail(), UserDTO.class);
        assertTrue(fetchedEntity.getStatusCode().is2xxSuccessful());
        assertEquals(fetchedEntity.getBody(), userDTO);
    }
}
