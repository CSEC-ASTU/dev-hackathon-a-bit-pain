package com.yaphet.devhackathonabitpain.services;

import com.yaphet.devhackathonabitpain.models.AppUser;
import com.yaphet.devhackathonabitpain.models.Division;
import com.yaphet.devhackathonabitpain.repositories.DivisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void create(Division division) {String divisionName= division.getDivisionName();
       boolean isNameExists=divisionRepository.findByDivisionName(divisionName).isPresent();
       if(isNameExists){
           throw new IllegalStateException(String.format("Division with a name=%s already exists",divisionName));
       }
        divisionRepository.save(division);

    }

    public Division getDivision(Long id) {
        Division division=divisionRepository.findById(id).orElseThrow(()->new IllegalStateException(String.format("Division not found with id=%d",id)));
        return division;
    }

    @Transactional
    public void update(Division division) {
        Long id=division.getId();
        Division tempDivision=divisionRepository.findById(id).orElseThrow(()->new IllegalStateException(String.format("Division not found with id=%d",id)));
        //TODO: check if there is real update
        divisionRepository.save(division);
    }




}
