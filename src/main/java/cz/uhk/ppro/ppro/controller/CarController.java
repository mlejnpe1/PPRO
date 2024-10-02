package cz.uhk.ppro.ppro.controller;

import cz.uhk.ppro.ppro.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    List<Car> cars= new ArrayList<>();

    @GetMapping("/")
        public String list(Model model) {
            model.addAttribute("cars", cars);
            return "index";
        }

    @GetMapping("/index/{index}")
    public String detail(Model model, @PathVariable int index) {
        if(index > -1 && index < cars.size()) {
            Car car = cars.get(index);
            model.addAttribute("car", car);
            return "index";
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{index}")
    public String delete(Model model, @PathVariable int index) {
        if(index > -1 && index < cars.size()) {
           cars.remove(index);
        }
        return "redirect:/";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("edit", false);
        return "edit";
    }

    @GetMapping("/edit/{index}")
    public String edit(Model model, @PathVariable int index) {
        if(index > -1 && index < cars.size()) {
            model.addAttribute("car", cars.get(index));
            model.addAttribute("edit", true);
            return "edit";
        }
        return "redirect:/";
    }

    @PostMapping("/save"){

    }
}
