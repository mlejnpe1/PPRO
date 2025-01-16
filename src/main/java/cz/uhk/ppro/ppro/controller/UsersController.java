package cz.uhk.ppro.ppro.controller;

import cz.uhk.ppro.ppro.model.Role;
import cz.uhk.ppro.ppro.model.User;
import cz.uhk.ppro.ppro.service.RoleService;
import cz.uhk.ppro.ppro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UsersController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("users", userService.findAll());
        return "users_list";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAll());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("error", "Passwords do not match!");
            model.addAttribute("roles", roleService.findAll());
            return "users/register";
        }

        userService.save(user);

        return "redirect:/users/";
    }

}
