package com.yaphet.devhackathonabitpain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="club_managements")
public class ClubManagement{

    @Id
    @SequenceGenerator(name = "club_sequence", sequenceName = "club_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "club_sequence")
    private Long id;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="president_id",referencedColumnName = "id")
    private AppUser president;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="vice_president_id",referencedColumnName = "id")
    private AppUser vicePresident;
}
