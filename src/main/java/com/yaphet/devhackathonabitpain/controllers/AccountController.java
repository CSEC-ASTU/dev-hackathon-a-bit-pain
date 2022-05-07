package com.yaphet.devhackathonabitpain.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@Controller
@RequestMapping("account")
public class AccountController {
    @GetMapping
    public String index(){
        return "account/home";
    }
    @GetMapping("/pending")
    public String pending(){
        return "/account/pending-page";
    }

}
