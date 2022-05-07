package com.yaphet.devhackathonabitpain.controllers;

import com.yaphet.devhackathonabitpain.models.AppUser;
import com.yaphet.devhackathonabitpain.models.Division;
import com.yaphet.devhackathonabitpain.services.DivisionService;
import com.yaphet.devhackathonabitpain.services.RegistrationService;
import com.yaphet.devhackathonabitpain.utilities.SelectPrivilege;
import com.yaphet.devhackathonabitpain.utilities.SelectedDivision;
import com.yaphet.devhackathonabitpain.utilities.enums.Gender;
import com.yaphet.devhackathonabitpain.utilities.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("")
public class RegistrationController {

    private final RegistrationService appUserRegistrationService;
    private final DivisionService divisionService;

    @GetMapping
    public String index(){
        return "landing-page";
    }
    @GetMapping("account/register")
    public String registrationForm(Model model){
        AppUser appUser=new AppUser();
        List<Gender> statusList= Arrays.asList(Gender.values());
        List<Division> divisionList=divisionService.getAllDivisions();
        model.addAttribute("appUser",appUser);
        model.addAttribute("genderList",statusList);
        model.addAttribute("divisionList",divisionList);
//        model.addAttribute("selectedDivision",new SelectedDivision(divisionList));
        return "registration/register-user";
    }
    @PostMapping("account/register")
    public String register(@Valid @ModelAttribute("appUser") AppUser appUser,BindingResult result){
        if(result.hasErrors()){
            return "registration/register-user";
        }
        appUserRegistrationService.register(appUser);
        return "redirect:/login";
    }
    @GetMapping("account/confirm")
    public String confirm(@RequestParam("token") String token){
        appUserRegistrationService.confirmToken(token);
        return "registration/email-verified";
    }


}
