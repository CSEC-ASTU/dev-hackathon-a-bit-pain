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
public class ActivationRequestController {

    private final ActivationRequestService activationRequestService;

    @GetMapping("user/request-list")
    public void getAllRequests(Model model){
        List<ActivationRequest> requestList=activationRequestService.findAll();
        model.addAttribute("requestList",requestList);
    }
    @GetMapping("user/request-list")
    public void getRequests(@RequestParam("status") Status status, Model model){
        List<ActivationRequest> requestList=activationRequestService.findByStatus(status);
        model.addAttribute("requestList",requestList);
    }
    @GetMapping("user/request-list")
    @PostMapping("user/activate")
    public void activate(@RequestParam("email") String email){
       activationRequestService.activate(email);
    }


}
