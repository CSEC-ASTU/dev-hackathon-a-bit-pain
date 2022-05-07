package com.yaphet.devhackathonabitpain.services;

import com.yaphet.devhackathonabitpain.models.ActivationRequest;
import com.yaphet.devhackathonabitpain.models.AppUser;
import com.yaphet.devhackathonabitpain.repositories.ActivationRequestRepository;
import com.yaphet.devhackathonabitpain.utilities.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ActivationRequestService {

    private final ActivationRequestRepository activationRequestRepository;
    private final AppUserService appUserService;

    public void save(AppUser appUser){
        if(appUser!=null){
            ActivationRequest activationRequest=new ActivationRequest();
            activationRequest.setAppUser(appUser);
            activationRequestRepository.save(activationRequest);
        }
        //TODO: handle null appuser
    }
    public List<ActivationRequest> findAll() {
        return activationRequestRepository.findAll();
    }

    public List<ActivationRequest> findByStatus(Status status) {
        List<ActivationRequest> requestList=activationRequestRepository.findByStatus(status).orElseThrow(
                ()->new IllegalStateException(String.format("activation request not found with status=%",status)));
        return requestList;
    }
    public void activate(String email){
        AppUser appUser=appUserService.getAppUserByEmail(email);
        appUserService.unlockAppUser(email);
        activationRequestRepository.updateStatus(appUser.getId(),Status.ACCEPTED);
    }
    public void decline(String email){
        AppUser appUser=appUserService.getAppUserByEmail(email);
        activationRequestRepository.updateStatus(appUser.getId(),Status.DECLINED);
    }



}
