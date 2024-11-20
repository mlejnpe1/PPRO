package cz.uhk.ppro.ppro.controller;

import cz.uhk.ppro.ppro.model.Driver;
import cz.uhk.ppro.ppro.service.DriverService;
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
@RequestMapping("/drivers")
public class DriverController {
    private DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("drivers", driverService.getAllDrivers());
        return "driver_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        Driver driver = driverService.getDriverById(id);
        if (driver != null) {
            model.addAttribute("driver", driver);
            return "driver_detail";
        }
        return "redirect:/drivers/";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable long id) {
        driverService.deleteDriverById(id);
        return "redirect:/drivers/";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("driver", new Driver());
        model.addAttribute("edit", false);
        return "driver_edit";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        Driver driver = driverService.getDriverById(id);
        if (driver != null) {
            model.addAttribute("driver", driver);
            model.addAttribute("edit", true);
            return "driver_edit";
        }
        return "redirect:/drivers/";
    }

    @PostMapping("/save")
    public String save(@Valid Driver driver, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "driver_edit";
        }
        driverService.saveDriver(driver);
        return "redirect:/drivers/";
    }
}
