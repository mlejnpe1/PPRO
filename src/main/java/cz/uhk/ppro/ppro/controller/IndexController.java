package cz.uhk.ppro.ppro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/access-denied")
    public String access() {
        return "access-denied";
    }

    @GetMapping("/403")
    public String forbidden() {
        return "403";
    }

    @GetMapping("/admin/")
    public String admin() {
        return "admin";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
