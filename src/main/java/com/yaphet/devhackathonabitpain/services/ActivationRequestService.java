package com.yaphet.devhackathonabitpain.services;

import com.yaphet.devhackathonabitpain.models.ActivationRequest;
import com.yaphet.devhackathonabitpain.models.AppUser;
import com.yaphet.devhackathonabitpain.repositories.ActivationRequestRepository;
import com.yaphet.devhackathonabitpain.utilities.email.EmailBuilder;
import com.yaphet.devhackathonabitpain.utilities.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ActivationRequestService {

    private final ActivationRequestRepository activationRequestRepository;
    private final AppUserService appUserService;
    private final EmailService emailSender;
    private final EmailBuilder emailBuilder;

    public void create(AppUser appUser){
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
    public void activate(Long id){
        //TODO: check if user is already activated
        AppUser appUser=appUserService.getAppUser(id);
        Status status=Status.ACCEPTED;
        appUserService.unlockAppUser(appUser.getEmail());
        changeRequestStatus(appUser,status);
    }
    public void decline(Long id){
        //TODO: check if user is already decline
        AppUser appUser=appUserService.getAppUser(id);
        Status status=Status.DECLINED;
        //TODO: disable user account
        changeRequestStatus(appUser,status);
    }
    private void changeRequestStatus(AppUser appUser,Status status){
        activationRequestRepository.updateStatus(appUser.getId(),status);
        emailSender.send(appUser.getEmail(),emailBuilder.buildEmail(appUser.getFirstName()+" "+appUser.getLastName(),status));
    }




}
