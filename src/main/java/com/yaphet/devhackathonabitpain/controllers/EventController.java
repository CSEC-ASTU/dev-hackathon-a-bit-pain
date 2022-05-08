package com.yaphet.devhackathonabitpain.controllers;

import com.yaphet.devhackathonabitpain.models.AppUser;
import com.yaphet.devhackathonabitpain.models.Event;
import com.yaphet.devhackathonabitpain.services.AppUserService;
import com.yaphet.devhackathonabitpain.services.EventService;
import com.yaphet.devhackathonabitpain.utilities.enums.Scope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("event")
public class EventController {
    private final EventService eventService;
    private final AppUserService appUserService;

    @GetMapping
    public String getEvents(Model model){
        List<Event> eventList=eventService.getEvents();
        model.addAttribute("eventList",eventList);
        return "event/event-list";
    }
    @GetMapping("/detail/{id}")
    public String getEvent(@PathVariable("id") Long id, Model model){
        Event event=eventService.getEvent(id);
        model.addAttribute("event",event);
        return "event/event-detail";
    }
    @GetMapping("/create")
    public String  createEventForm(Model model){
        Event event=new Event();
        List<AppUser> appUserList=appUserService.getAppUsers();
        List<Scope> scopeList= Arrays.asList(Scope.values());
        model.addAttribute("event",event);
        model.addAttribute("appUserList",appUserList);
        model.addAttribute("scopeList",scopeList);
        return "event/event-create";
    }
    @PostMapping("/create")
    public String createEvent(@Valid @ModelAttribute Event event, BindingResult result,RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "redirect:event/createEvent";
        }
        eventService.create(event);
        redirectAttributes.addAttribute("id",event.getId());
        if(event.getScope()==Scope.ALL){
            return "redirect:/event";
        }
        return "redirect:/event/invite-members/{id}";
    }
    @GetMapping("/invite-members/{id}")
    public String inviteMembersForm(@PathVariable("id") Long id,Model model){
        List<AppUser> appUserList=appUserService.getAppUsers();
        Event event=eventService.getEvent(id);
        model.addAttribute("event",event);
        model.addAttribute("appUserList",appUserList);
        return "event/invite-members";
    }
    @PostMapping("/invite-members")
    public String inviteMembers(@ModelAttribute Event event, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "redirect:/event/invite-members/{id}";
        }
        Event event1=eventService.getEvent(event.getId());
        eventService.update(event1);
        redirectAttributes.addAttribute("id",event.getId());
        return "redirect:/event}";
    }
    @GetMapping("/update/{id}")
    public String updateEvent(@PathVariable("id") Long id,Model model){
        Event event=eventService.getEvent(id);
        model.addAttribute("event",event);
        return "event/edit-event";
    }
    @PostMapping("/update")
    public String updateEvent(@Valid @ModelAttribute Event event, BindingResult result){
        if(result.hasErrors()){
            return "redirect:event/updateEvent";
        }
        eventService.update(event);
        return "redirect:/event";
    }
    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable("id") Long id){
        eventService.delete(id);
        return "redirect:/event";
    }


}
