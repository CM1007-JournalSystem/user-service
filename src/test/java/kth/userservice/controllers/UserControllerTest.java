package kth.userservice.controllers;

import kth.userservice.DTO.UserDTO;
import kth.userservice.models.Gender;
import kth.userservice.models.User;
import kth.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private UserDTO testUserDTO;
    private User testUser;

    @BeforeEach
    void setUp() {
        testUserDTO = new UserDTO();
        testUserDTO.setId(1);
        testUserDTO.setUsername("testUser");
        testUserDTO.setSocial_security("123456-7890");
        testUserDTO.setFirst_name("Test");
        testUserDTO.setLast_name("User");
        testUserDTO.setGender(Gender.MALE);
        testUserDTO.setEmail("test@example.com");
        testUserDTO.setPhoneNr("0701234567");
        testUserDTO.setKeycloak_id(UUID.randomUUID());

        testUser = testUserDTO.DTOtoUser();
    }

    @Test
    void testRegister_Success() {
        when(userService.registerUser(any(UserDTO.class))).thenReturn(true);

        ResponseEntity<String> response = userController.register(testUserDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("User registered", response.getBody());
    }

    @Test
    void testRegister_Failure() {
        when(userService.registerUser(any(UserDTO.class))).thenReturn(false);

        ResponseEntity<String> response = userController.register(testUserDTO);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Username already exists or couldn't be registered", response.getBody());
    }

    @Test
    void testGetAllUsers() {
        when(userService.getAllUsers()).thenReturn(List.of(testUser));

        List<UserDTO> users = userController.getAllUsers();

        assertEquals(1, users.size());
    }

    @Test
    void testGetUserById_Found() {
        when(userService.getUserById(eq(1))).thenReturn(Optional.of(testUser));

        ResponseEntity<UserDTO> response = userController.getUserById(1);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetUserById_NotFound() {
        when(userService.getUserById(eq(1))).thenReturn(Optional.empty());

        ResponseEntity<UserDTO> response = userController.getUserById(1);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testGetUserByKeyCloak_Found() {
        UUID keycloakId = UUID.randomUUID();
        when(userService.getUserByKeyCloak(eq(keycloakId))).thenReturn(Optional.of(testUser));

        ResponseEntity<UserDTO> response = userController.getUserByKeyCloak(keycloakId);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetUserByKeyCloak_NotFound() {
        UUID keycloakId = UUID.randomUUID();
        when(userService.getUserByKeyCloak(eq(keycloakId))).thenReturn(Optional.empty());

        ResponseEntity<UserDTO> response = userController.getUserByKeyCloak(keycloakId);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}
