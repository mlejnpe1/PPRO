package cz.uhk.ppro.ppro;

import cz.uhk.ppro.ppro.model.Role;
import cz.uhk.ppro.ppro.model.User;
import cz.uhk.ppro.ppro.service.RoleService;
import cz.uhk.ppro.ppro.service.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserServiceImpl userService;
    private final RoleService roleService;

    public DataInitializer(UserServiceImpl userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

        roleService.saveRole(adminRole);
        roleService.saveRole(userRole);

        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        //roles.add(userRole);

        String encodedPassword = passwordEncoder.encode("heslo");

        User user = new User("admin", encodedPassword, roles);
        userService.save(user);

        System.out.println("Encoded password: " + encodedPassword + " Registered user is: " + user.getUsername());
        System.out.println("Initial data loaded.");

    }
}
