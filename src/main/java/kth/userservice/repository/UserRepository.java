package kth.userservice.repository;

import java.util.UUID;
import kth.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    List<User> getUserByEmail(String email);

    Optional<User> getUserById(int id);

    List<User> findAll();

    @Query("SELECT u FROM Users u WHERE u.keycloak_id = :id")
    Optional<User> getUserBykeycloakId(UUID id);

}
