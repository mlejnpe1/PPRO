package cz.uhk.ppro.ppro.service;

import cz.uhk.ppro.ppro.model.Role;
import cz.uhk.ppro.ppro.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface RoleService {
    Role saveRole(Role role);
    Optional<Role> findByName(String name);
    List<Role> findAll();

}
