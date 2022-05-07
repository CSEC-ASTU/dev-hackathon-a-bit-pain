package com.yaphet.devhackathonabitpain.controllers;

import com.yaphet.devhackathonabitpain.models.ActivationRequest;
import com.yaphet.devhackathonabitpain.models.AppUser;
import com.yaphet.devhackathonabitpain.services.ActivationRequestService;
import com.yaphet.devhackathonabitpain.utilities.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("user/request")
public class ActivationRequestController {

    private final ActivationRequestService activationRequestService;

    @GetMapping
    public void getAllRequests(Model model){
        List<ActivationRequest> requestList=activationRequestService.findAll();
        model.addAttribute("requestList",requestList);
    }
    @GetMapping("/{status}")
    public void getRequests(@PathVariable("status") Status status, Model model){
        List<ActivationRequest> requestList=activationRequestService.findByStatus(status);
        model.addAttribute("requestList",requestList);
    }
    @PostMapping("/activate")
    public void activate(@RequestParam("email") String email){
       activationRequestService.activate(email);
    }
    @PostMapping("/decline")
    public void decline(@RequestParam("email") String email){
        activationRequestService.decline(email);
    }


}
