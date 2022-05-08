package com.yaphet.devhackathonabitpain.services;

import com.yaphet.devhackathonabitpain.models.Division;
import com.yaphet.devhackathonabitpain.models.Tag;
import com.yaphet.devhackathonabitpain.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TagService {
    private final TagRepository tagRepository;
    public List<Tag> getAllTags(){
        return tagRepository.findAll();
    }

    public Tag getTag(Long id) {
        Tag tag=tagRepository.findById(id).orElseThrow(()->new IllegalStateException(String.format("Tag not found with id=%d",id)));
        return tag;
    }
    public void delete(Long id) {
        Tag tag=tagRepository.findById(id).orElseThrow(()->new IllegalStateException(String.format("Tag not found with id=%d",id)));
        tagRepository.deleteById(id);
    }
    public void create(Tag tag) {
        //TODO: check if tag name already exist
        tagRepository.save(tag);
    }
    @Transactional
    public void update(Tag tag) {
        //TODO: check if tag name already exist
        tagRepository.save(tag);
    }
}
