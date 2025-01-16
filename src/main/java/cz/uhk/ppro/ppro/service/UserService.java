package cz.uhk.ppro.ppro.service;

import cz.uhk.ppro.ppro.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService extends UserDetailsService {
    Optional<User> findByUsername(String username);

    List<User> findAll();

    void save(User user);

    void delete(Long id);
}
