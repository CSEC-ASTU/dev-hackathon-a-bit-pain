package com.yaphet.devhackathonabitpain.services;

import com.yaphet.devhackathonabitpain.models.Tag;
import com.yaphet.devhackathonabitpain.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TagService {
    private final TagRepository tagRepository;
    public List<Tag> getAllTags(){
        return tagRepository.findAll();
    }
}
