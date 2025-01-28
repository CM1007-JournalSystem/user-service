package kth.userservice.repository;

import java.lang.util;
import kth.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    List<User> getUserByEmail(String email);

    Optional<User> getUserById(int id);

    List<User> findAll();

    List<User> findUsersByRoleIn(List<String> roles);

    Optional<User> getUserByKeyCloakId(UUID id);

}
