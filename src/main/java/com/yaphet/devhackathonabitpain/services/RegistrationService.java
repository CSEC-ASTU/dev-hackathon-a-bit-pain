package com.yaphet.devhackathonabitpain.services;

import com.yaphet.devhackathonabitpain.utilities.email.EmailBuilder;
import com.yaphet.devhackathonabitpain.utilities.email.EmailValidator;
import com.yaphet.devhackathonabitpain.models.AppUser;
import com.yaphet.devhackathonabitpain.models.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.time.LocalDateTime;


@AllArgsConstructor
@Service
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final AppUserService appUserService;
    private final AppUserRoleService appUserRoleService;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailService emailSender;
    private final EmailBuilder emailBuilder;
    private ActivationRequestService activationRequestService;

    public void register(AppUser appUser) {
        boolean isEmailValid=emailValidator.test(appUser.getEmail());
        if(!isEmailValid){
            throw new IllegalStateException("Invalid email address");
        }
        String token= appUserService.signUpUser(appUser);
        //assign default role to new user
        appUserRoleService.assignRole(appUser.getEmail(),"INACTIVE_USER");
        String link="http://10.240.73.32:8080/confirm?token="+token;
        emailSender.send(appUser.getEmail(),emailBuilder.buildEmail(appUser.getFirstName()+" "+appUser.getLastName(),link));
        activationRequestService.create(appUser);
    }
    @Transactional
    public void confirmToken(@RequestParam("token") String token){
        ConfirmationToken confirmationToken=confirmationTokenService.getToken(token).orElseThrow(()->new IllegalStateException("token not found"));
        if(confirmationToken.getConfirmedAt()!=null){
            throw new IllegalStateException("email already confirmed");
        }
        LocalDateTime expiresAt=confirmationToken.getExpiresAt();
        if(expiresAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("token confirmed");
        }
        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());

    }


}
