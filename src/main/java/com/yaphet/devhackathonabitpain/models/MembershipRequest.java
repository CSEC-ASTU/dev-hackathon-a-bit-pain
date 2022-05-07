package com.yaphet.devhackathonabitpain.models;

import com.yaphet.devhackathonabitpain.utilities.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="membership_requests")
public class MembershipRequest {

    @Id
    @SequenceGenerator(name = "club_sequence", sequenceName = "club_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "club_sequence")
    private Long id;
//    @NotNull
    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;
    @NotNull
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinColumn(name = "division_id")
    private Set<Division> divisions=new HashSet<>();
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime requestAt=LocalDateTime.now();
    @NotNull
    private Status status;


}
