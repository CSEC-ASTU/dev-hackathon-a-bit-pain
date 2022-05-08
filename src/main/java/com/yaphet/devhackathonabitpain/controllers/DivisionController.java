package com.yaphet.devhackathonabitpain.controllers;

import com.yaphet.devhackathonabitpain.models.AppUser;
import com.yaphet.devhackathonabitpain.models.Division;
import com.yaphet.devhackathonabitpain.services.AppUserService;
import com.yaphet.devhackathonabitpain.services.DivisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("division")
public class DivisionController {

    private final DivisionService divisionService;
    private final AppUserService appUserService;

    @GetMapping
    public void getDivisions(){
        divisionService.getAllDivisions();
    }
    @GetMapping("/detail/{id}")
    public String getDivision(@PathVariable("id") Long id,Model model){
        Division division=divisionService.getDivision(id);
        model.addAttribute("division",division);
        return "division/division-detail";
    }
    @GetMapping("/create")
    public String  createDivisionForm(Model model){
        Division division=new Division();
        model.addAttribute("division",division);
        return "divison/division-reate";
    }
    @PostMapping("/create")
    public String createDivision(@Valid @ModelAttribute Division division, BindingResult result){
        if(result.hasErrors()){
            return "redirect:division/create";
        }
        divisionService.create(division);
        return "redirect:/division/add-head";
    }
    @GetMapping("/add-head/{id}")
    public String addHeadForm(@PathVariable("id") Long id,Model model){
        Division division=divisionService.getDivision(id);
        List<AppUser> appUserList=appUserService.getAppUsers();
        model.addAttribute("id",id);
        model.addAttribute("appUserList",appUserList);
        return "division/add-head";
    }
    @PostMapping("/add-head")
    public String addHead(@Valid @ModelAttribute Division division, BindingResult result, RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("id",division.getId());
        if(result.hasErrors()){
            return "redirect:/division/add-head/{id}";
        }
        divisionService.update(division);
        return "redirect:/division";
    }

}
