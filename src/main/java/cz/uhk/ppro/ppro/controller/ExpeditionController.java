package cz.uhk.ppro.ppro.controller;

import cz.uhk.ppro.ppro.model.Expedition;
import cz.uhk.ppro.ppro.service.ExpeditionService;
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
@RequestMapping("/expeditions")
public class ExpeditionController {

    private ExpeditionService expeditionService;

    @Autowired
    public ExpeditionController(ExpeditionService expeditionService){
        this.expeditionService = expeditionService;
    }

    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("expeditions", expeditionService.getAllExpeditions());
        return "expedition_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id){
        Expedition expedition = expeditionService.getExpeditionById(id);
        if(expedition != null){
            model.addAttribute("expedition", expedition);
            return "expedition_detail";
        }
        return "expedition_detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        if(expeditionService.getExpeditionById(id)!=null) {
            expeditionService.deleteExpeditionById(id);
        }
        return "redirect:/expeditions/";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        Expedition expedition = expeditionService.getExpeditionById(id);
        if(expedition != null) {
            model.addAttribute("expedition", expedition);
            model.addAttribute("edit", true);
            model.addAttribute("expeditions", expeditionService.getAllExpeditions());
            return "expedition_edit";
        }
        return "redirect:/expeditions/";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("expedition", new Expedition());
        model.addAttribute("edit", false);
        return "expedition_edit";
    }

    @PostMapping("/save")
    public String save(@Valid Expedition expedition, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("edit", true);
            return "expedition_edit";
        }
        expeditionService.saveExpedition(expedition);
        return "redirect:/expeditions/";
    }
}
