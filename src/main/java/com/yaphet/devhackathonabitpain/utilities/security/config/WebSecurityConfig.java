package com.yaphet.devhackathonabitpain.utilities.security.config;

import com.yaphet.devhackathonabitpain.services.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/account/pending")
                .hasAnyRole("USER","INACTIVE_USER","ADMIN","SUPER_ADMIN","COMMITTEE")
                .antMatchers("/user/","user/detail/**","/event/**","/post/**","/tag/**","/division/**")
                .hasAnyRole("ADMIN","SUPER_ADMIN","COMMITTEE")
                .antMatchers("/user/create/**","/user/update/**","/user/delete/**","club/**")
                .hasAnyRole("ADMIN","SUPER_ADMIN")
                .antMatchers("/register/**","/confirm/**","/login/**","/")
                .permitAll()
                .anyRequest()
                .authenticated().and()
                .formLogin().successHandler(authenticationSuccessHandler)
                .permitAll()
//                .defaultSuccessUrl("/user/home")
                .and().logout().permitAll()
                ;
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(daoAuthenticationProvider());
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }
}
