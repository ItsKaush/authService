package dev.kaushar.authservice.authservice.repositories;

import dev.kaushar.authservice.authservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    User save(User user);

    Optional<User> findUserById(Long Id);
    Optional<User> findUserByEmailId(String emailId);
}
