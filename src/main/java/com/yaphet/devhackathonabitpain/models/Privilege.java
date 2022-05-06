package com.yaphet.devhackathonabitpain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Privilege {
    @Id
    @SequenceGenerator(name = "privilege_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "privilege_sequence")
    private Long id;
    @NotBlank
    private String privilegeName;
    public Privilege(String privilegeName){
        this.privilegeName=privilegeName;
    }

}
