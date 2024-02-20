package se.ju23.typespeeder.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import se.ju23.typespeeder.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsernameAndPassword(String username, String password);
    Optional<User> findByUsername(String username); //ska jag ha med det här ens?
}

