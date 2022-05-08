package com.yaphet.devhackathonabitpain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="divisions")
public class Division {

    @Id
    @SequenceGenerator(name = "division_sequence", sequenceName = "division_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "division_sequence")
    private Long id;
    @NotBlank
    private String divisionName;
    @NotBlank
    private String description;
    //@NotNull
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="app_user_id",referencedColumnName = "id")
    private AppUser head;
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name="division_members",
            joinColumns = @JoinColumn(name="division_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="app_user_id",referencedColumnName = "id")
    )
    private Set<AppUser> members=new HashSet<>();
    @NotNull
    private boolean deleted=false;
}
