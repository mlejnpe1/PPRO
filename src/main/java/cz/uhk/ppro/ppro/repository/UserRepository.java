package cz.uhk.ppro.ppro.repository;

import cz.uhk.ppro.ppro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    void deleteUserById(Long id);
}
