package cz.uhk.ppro.ppro.controller;

import cz.uhk.ppro.ppro.model.Car;
import cz.uhk.ppro.ppro.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
        public String list(Model model) {
            model.addAttribute("cars", carService.getAllCars());
            return "car_list";
        }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        Car car = carService.getCarById(id);
        if(car != null) {
            model.addAttribute("car", car);
            return "car_detail";
        }
        //return "redirect:/cars/";
        return "car_detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable long id) {
        if(id > -1 && id < carService.getAllCars().size()) {
           carService.deleteCarById(id);
        }
        return "redirect:/cars/";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("edit", false);
        return "car_edit";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        Car car = carService.getCarById(id);
        if(car != null) {
            model.addAttribute("car", car);
            model.addAttribute("edit", true);
            return "car_edit";
        }
        return "redirect:/cars/";
    }


    @PostMapping("/save")
    public String save(@Valid Car car, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "car_edit";
        }
        carService.saveCar(car);
        return "redirect:/cars/";
    }
}
