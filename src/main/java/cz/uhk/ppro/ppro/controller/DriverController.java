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

    @GetMapping("/index/{index}")
    public String detail(Model model, @PathVariable int index) {
        if(index > -1 && index < driverService.getAllDrivers().size()) {
            Driver driver = driverService.getDriverById(index);
            model.addAttribute("driver", driver);
            return "driver_list";
        }
        return "redirect:/drivers/";
    }

    @GetMapping("/delete/{index}")
    public String delete(Model model, @PathVariable int index) {
        if(index > -1 && index < driverService.getAllDrivers().size()) {
            driverService.deleteDriverById(index);
        }
        return "redirect:/drivers/";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("driver", new Driver());
        model.addAttribute("edit", false);
        return "edit_edit";
    }

    @GetMapping("/edit/{index}")
    public String edit(Model model, @PathVariable int index) {
        Driver driver = driverService.getDriverById(index);
        if(driver != null) {
            driver.setId(index);
            model.addAttribute("driver", driver);
            model.addAttribute("edit", true);
            return "edit_edit";
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
