package kth.userservice.service;

import java.util.UUID;
import kth.userservice.DTO.UserDTO;
import kth.userservice.models.User;
import kth.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public Optional<User> getUserById(int id) {
        return userRepository.getUserById(id);
    }


    public boolean registerUser(UserDTO userDTO) {
        Optional<User> userOpt = userRepository.findByUsername(userDTO.getUsername());
        if (userOpt.isPresent())
            return false;
        User user = userDTO.DTOtoUser();
        userRepository.save(user);
        return true;
    }

    public Optional<User> getUserByKeyCloak(UUID id) {
        return userRepository.getUserBykeycloakId(id);
    }
}
