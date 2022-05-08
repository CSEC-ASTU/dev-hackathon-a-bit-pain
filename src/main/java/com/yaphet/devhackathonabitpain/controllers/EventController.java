package com.yaphet.devhackathonabitpain.controllers;

import com.yaphet.devhackathonabitpain.models.AppUser;
import com.yaphet.devhackathonabitpain.models.Division;
import com.yaphet.devhackathonabitpain.models.Event;
import com.yaphet.devhackathonabitpain.models.Tag;
import com.yaphet.devhackathonabitpain.services.AppUserService;
import com.yaphet.devhackathonabitpain.services.EventService;
import com.yaphet.devhackathonabitpain.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("event")
public class EventController {
    private final EventService eventService;
    private final AppUserService appUserService;
    private final TagService tagService;

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
        List<Tag> tagList=tagService.getAllTags();
        model.addAttribute("event",event);
        model.addAttribute("appUserList",appUserList);
        model.addAttribute("tagList",tagList);
        return "event/event-create";
    }
    @PostMapping("/create")
    public String createEvent(@Valid @ModelAttribute Event event, BindingResult result){
        if(result.hasErrors()){
            return "redirect:event/create";
        }
        eventService.create(event);
        return "redirect:/event/invite-members";
    }
    @GetMapping("/invite-members/{id}")
    public String inviteMembersForm(@PathVariable("id") Long id,Model model){
        List<AppUser> appUserList=appUserService.getAppUsers();
        model.addAttribute("id",id);
        model.addAttribute("appUserList",appUserList);
        return "event/invite-members";
    }
    @PostMapping("/invite-members")
    public String inviteMembers(@Valid @ModelAttribute Event event, BindingResult result, RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("id",event.getId());
        if(result.hasErrors()){
            return "redirect:/event/invite-members/{id}";
        }
        eventService.update(event);
        return "redirect:/event/detail/{id}";
    }
    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable("id") Long id){
        eventService.delete(id);
        return "redirect:/event";
    }


}
