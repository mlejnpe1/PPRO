package cz.uhk.ppro.ppro.service;

import org.springframework.stereotype.Service;

@Service
public interface UserRoleService {
    String assignRoleToUser(String username, String roleName);
}
