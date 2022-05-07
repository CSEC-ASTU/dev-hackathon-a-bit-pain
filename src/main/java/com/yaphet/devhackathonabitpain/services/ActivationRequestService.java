package com.yaphet.devhackathonabitpain.services;

import com.yaphet.devhackathonabitpain.models.ActivationRequest;
import com.yaphet.devhackathonabitpain.models.AppUser;
import com.yaphet.devhackathonabitpain.repositories.ActivationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ActivationRequestService {

    private final ActivationRequestRepository activationRequestRepository;
    public void save(AppUser appUser){
        if(appUser!=null){
            ActivationRequest activationRequest=new ActivationRequest();
            activationRequest.setAppUser(appUser);
            activationRequestRepository.save(activationRequest);
        }
        //TODO: handle null appuser
    }
}
