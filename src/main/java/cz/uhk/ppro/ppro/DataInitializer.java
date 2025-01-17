package cz.uhk.ppro.ppro;

import cz.uhk.ppro.ppro.model.Role;
import cz.uhk.ppro.ppro.model.User;
import cz.uhk.ppro.ppro.service.RoleService;
import cz.uhk.ppro.ppro.service.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");

        roleService.saveRole(adminRole);
        roleService.saveRole(userRole);

        Set<Role> admin_role = new HashSet<>();
        admin_role.add(adminRole);
        Set<Role> user_role = new HashSet<>();
        user_role.add(userRole);


        String encodedPassword = passwordEncoder.encode("heslo");

        List<User> users = new ArrayList<>();

        users.add(new User("admin", encodedPassword, admin_role));
        users.add(new User("Jožo", encodedPassword, user_role));
        users.add(new User("Evžen", encodedPassword, user_role));
        users.add(new User("Ivan", encodedPassword, user_role));

        for (User u:users
             ) {
            userService.save(u);
        }

    }
}
