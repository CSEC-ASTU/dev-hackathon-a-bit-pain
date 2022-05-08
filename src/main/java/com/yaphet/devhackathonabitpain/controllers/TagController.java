package com.yaphet.devhackathonabitpain.controllers;

import com.yaphet.devhackathonabitpain.models.Tag;
import com.yaphet.devhackathonabitpain.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("post/tags")
public class TagController {

    private final TagService tagService;

    @GetMapping
    public String getAllTags(Model model){
        List<Tag> tagList=tagService.getAllTags();
        model.addAttribute("tagList",tagList);
        return "post/tag-list";
    }
    @GetMapping("/detail/{id}")
    public String getTag(@PathVariable("id") Long id,Model model){
        Tag tag=tagService.getTag(id);
        model.addAttribute("tag",tag);
        return "post/tag-detail";
    }
    @GetMapping("/create")
    public String createForm(Model model){
        Tag tag=new Tag();
        model.addAttribute("tag",tag);
        return "post/create-tag";
    }
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute Tag tag, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/post/tag/create";
        }
        tagService.create(tag);
        return "redirect:/post/tag/";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id,Model model){
        Tag tag=tagService.getTag(id);
        model.addAttribute("tag",tag);
        return "post/edit-tag";
    }
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute Tag tag, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/post/tag/update";
        }
        tagService.update(tag);
        return "redirect:/post/tag/";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        tagService.delete(id);
        return "redirect:/post/tag/";
    }

}
