package cz.uhk.ppro.ppro;

import cz.uhk.ppro.ppro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class PproApplication{

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public PproApplication(UserService userService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(PproApplication.class, args);
    }

}
