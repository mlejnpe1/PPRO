package cz.uhk.ppro.ppro.controller;

import cz.uhk.ppro.ppro.model.User;
import cz.uhk.ppro.ppro.service.RoleService;
import cz.uhk.ppro.ppro.service.UserRoleService;
import cz.uhk.ppro.ppro.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserRoleService userRoleService;

    public UserController(UserService userService, RoleService roleService, UserRoleService userRoleService) {
        this.userService = userService;
        this.roleService = roleService;
        this.userRoleService = userRoleService;
    }

    @GetMapping("/addUser")
    public String showAddUserForm() {
        return "addUser";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.save(user);
        return "redirect:/admin/addUser?success";
    }

    @GetMapping("/assignRole")
    public String showAssignRoleForm() {
        return "assignRole";
    }

    @PostMapping("/assignRole")
    public String assignRole(@RequestParam String username, @RequestParam String roleName) {
        String result = userRoleService.assignRoleToUser(username, roleName);
        return "redirect:/admin/assignRole?message=" + result;
    }
}

