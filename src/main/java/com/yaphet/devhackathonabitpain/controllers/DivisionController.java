package com.yaphet.devhackathonabitpain.controllers;

import com.yaphet.devhackathonabitpain.services.DivisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("division")
public class DivisionController {

    private final DivisionService divisionService;

    @GetMapping
    public void getDivisions(){
        divisionService.getAllDivisions();
    }

}
