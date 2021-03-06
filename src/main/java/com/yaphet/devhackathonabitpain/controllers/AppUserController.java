package com.yaphet.devhackathonabitpain.controllers;

import com.yaphet.devhackathonabitpain.models.AppUser;
import com.yaphet.devhackathonabitpain.models.Role;
import com.yaphet.devhackathonabitpain.services.AppUserService;
import com.yaphet.devhackathonabitpain.services.RoleService;
import com.yaphet.devhackathonabitpain.utilities.SelectedRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("user")
public class AppUserController {
    private final AppUserService appUserService;
    private final RoleService roleService;


    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public String getAppUsers(Model model){
        List<AppUser> appUserList=appUserService.getAppUsers();
        model.addAttribute("appUserList",appUserList);
        return "appuser/appuser-list";
    }
    @GetMapping("/detail/{id}")
    public String getAppUser(@PathVariable("id") Long id,Model model){
        AppUser appUser=appUserService.getAppUser(id);
        model.addAttribute("appUser",appUser);
        return "/appuser/appuser-detail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id,Model model){
        AppUser appUser=appUserService.getAppUser(id);
        model.addAttribute("appUser",appUser);
        return "/appuser/appuser-update";
    }
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute AppUser appUSer,BindingResult result,RedirectAttributes redirectAttr){
        redirectAttr.addAttribute("id",appUSer.getId());
        if(result.hasErrors()){
            return "redirect:/user/update";
        }
        appUserService.update(appUSer);
        return "redirect:/user/detail/{id}";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        appUserService.delete(id);
        return "redirect:/user";
    }

    @GetMapping("/assign-role/{id}")
    public String assignRoleForm(@PathVariable("id") Long id,Model model){
        AppUser appUser=appUserService.getAppUser(id);
        List<Role> roleList=roleService.getRoleList();
        model.addAttribute("appUser",appUser);
        model.addAttribute("selectedRole",new SelectedRole(roleList));
        return "appuser/assign-role";
    }
    @PostMapping("/assign-role")
    public String assignRole(@RequestParam("id") Long id,@Valid @ModelAttribute SelectedRole selectedRoles,BindingResult result,RedirectAttributes redirectAttributes){
        AppUser appUser=appUserService.getAppUser(id);
        redirectAttributes.addAttribute("id",id);
        if(result.hasErrors()){
            return "redirect:/user/assign-role/{id}";
        }
        appUser.setRoles(new HashSet<>(selectedRoles.getSelectedRoles()));
        appUserService.updateAppUserRole(appUser);
        return "redirect:/user/detail/{id}";
    }


}
