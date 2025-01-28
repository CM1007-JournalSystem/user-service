package kth.userservice.controllers;

import java.util.UUID;
import kth.userservice.DTO.UserDTO;
import kth.userservice.models.User;
import kth.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO){
        try{
        boolean isRegistered = userService.registerUser(userDTO);

        if(isRegistered){
            return ResponseEntity.ok("User registered");
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists or couldn't be registered");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Couldn't register user");
        }
    }

    @GetMapping("/getAllUsers")
    public List<UserDTO> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return users.stream().map(User::UserToDTO).toList();
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        Optional<User> user = userService.getUserById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get().UserToDTO()); // Assuming you have UserToDTO method
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/getUserByKeyCloak/{keyCloakId}")
    public ResponseEntity<UserDTO> getUserByKeyCloak(@PathVariable UUID keyCloakId) {
        Optional<User> user = userService.getUserByKeyCloak(keyCloakId);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get().UserToDTO()); // Assuming you have UserToDTO method
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
