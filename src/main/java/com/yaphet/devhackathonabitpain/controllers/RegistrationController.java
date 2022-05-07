package com.yaphet.devhackathonabitpain.controllers;

import com.yaphet.devhackathonabitpain.models.ActivationRequest;
import com.yaphet.devhackathonabitpain.services.RegistrationService;
import com.yaphet.devhackathonabitpain.models.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("")
public class RegistrationController {

    private final RegistrationService appUserRegistrationService;
    private final ActivationRequest activationRequest;

    @GetMapping
    public String index(){
        return "landing-page";
    }
    @GetMapping("account/register")
    public String registrationForm(Model model){
        AppUser appUser=new AppUser();
        model.addAttribute("appUser",appUser);
        return "registration/register-user";
    }
    @PostMapping("account/register")
    public String register(@Valid @ModelAttribute("appUser") AppUser appUser, BindingResult result){
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
