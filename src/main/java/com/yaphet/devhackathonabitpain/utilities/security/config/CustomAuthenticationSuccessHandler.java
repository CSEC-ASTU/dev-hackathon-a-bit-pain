package com.yaphet.devhackathonabitpain.utilities.security.config;

import com.yaphet.devhackathonabitpain.models.AppUser;
import com.yaphet.devhackathonabitpain.services.AppUserService;
import com.yaphet.devhackathonabitpain.utilities.AppUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final AppUserService appUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String email=((AppUserDetails)authentication.getPrincipal()).getUsername();
        AppUser appUser=appUserService.getAppUserByEmail(email);
        if(appUser.getLocked()){
            response.sendRedirect("user/pending");
        }else{
            response.sendRedirect("user/home");
        }


    }
}
