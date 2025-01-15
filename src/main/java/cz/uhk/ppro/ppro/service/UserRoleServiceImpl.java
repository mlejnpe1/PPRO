package cz.uhk.ppro.ppro.service;

import cz.uhk.ppro.ppro.model.Role;
import cz.uhk.ppro.ppro.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService{

    private final UserService userService;
    private final RoleService roleService;

    public UserRoleServiceImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public String assignRoleToUser(String username, String roleName) {
        Optional<User> userOptional = userService.findByUsername(username);
        Optional<Role> roleOptional = roleService.findByName(roleName);

        if (userOptional.isPresent() && roleOptional.isPresent()) {
            User user = userOptional.get();
            Role role = roleOptional.get();
            user.getRoles().add(role);
            userService.save(user);
            return "Role successfully assigned!";
        } else {
            return "User or role not found.";
        }
    }
}
