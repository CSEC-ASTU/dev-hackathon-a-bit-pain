package com.yaphet.devhackathonabitpain.services;

import com.yaphet.devhackathonabitpain.models.AppUser;
import com.yaphet.devhackathonabitpain.models.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class AppUserRoleService {

    private final AppUserService appUserService;
    private final RoleService roleService;

    public void assignRole(String email,String roleName) {
        AppUser appUser = appUserService.getAppUserByEmail(email);
        Role role = roleService.getRoleByName(roleName);
        Set<Role> roles = appUser.getRoles();
        roles.add(role);
        appUser.setRoles(roles);
        appUserService.updateAppUserRole(appUser);
    }
}
