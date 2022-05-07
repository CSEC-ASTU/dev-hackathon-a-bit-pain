package com.yaphet.devhackathonabitpain.services;

import com.yaphet.devhackathonabitpain.models.Division;
import com.yaphet.devhackathonabitpain.repositories.DivisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DivisionService {

    private final DivisionRepository divisionRepository;

    public void save(Division division){
        if(division!=null){
            divisionRepository.save(division);
        }
        //TODO: handle null division
    }
    public List<Division> getAllDivisions() {
        return divisionRepository.findAll();
    }
}
