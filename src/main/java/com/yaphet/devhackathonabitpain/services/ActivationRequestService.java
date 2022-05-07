package com.yaphet.devhackathonabitpain.services;

import com.yaphet.devhackathonabitpain.models.ActivationRequest;
import com.yaphet.devhackathonabitpain.models.AppUser;
import com.yaphet.devhackathonabitpain.models.Role;
import com.yaphet.devhackathonabitpain.repositories.ActivationRequestRepository;
import com.yaphet.devhackathonabitpain.utilities.email.EmailBuilder;
import com.yaphet.devhackathonabitpain.utilities.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ActivationRequestService {

    private final ActivationRequestRepository activationRequestRepository;
    private final AppUserService appUserService;
    private final RoleService roleService;
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
    public boolean activate(Long id){
        AppUser appUser=appUserService.getAppUser(id);
        Status status=Status.ACCEPTED;
        if(activationRequestRepository.userRequest(appUser.getId(),Status.ACCEPTED).size()!=0){
            return false;
        }
        Role role=roleService.getRoleByName("USER");
        //set lock=false
        appUserService.unlockAppUser(appUser.getEmail());
        //update role from INACTIVE_USER to USER
        appUser.setRoles(new HashSet<>(Set.of(role)));
        appUserService.updateAppUserRole(appUser);
        //Change request status to ACCEPTED
        changeRequestStatus(appUser,status);
        return true;
    }


    public boolean decline(Long id){
        AppUser appUser=appUserService.getAppUser(id);
        Status status=Status.DECLINED;
        if(activationRequestRepository.userRequest(appUser.getId(),status).size()!=0){
            return false;
        }
        //TODO: disable user account
        changeRequestStatus(appUser,status);
        return true;
    }
    private void changeRequestStatus(AppUser appUser,Status status){
        activationRequestRepository.updateStatus(appUser.getId(),status);
        emailSender.send(appUser.getEmail(),emailBuilder.buildEmail(appUser.getFirstName()+" "+appUser.getLastName(),status));
    }





}
