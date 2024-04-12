package se.ju23.typespeeder.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import se.ju23.typespeeder.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //boolean existsByUsernameAndPassword(String username, String password);
    Optional<User> findByUsernameAndPassword(String username, String password); //ska jag ha med det här ens?
    List<User> findAllByOrderByExperienceDesc();
}

