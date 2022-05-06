package com.yaphet.devhackathonabitpain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="events")
public class Event {
    @Id
    @SequenceGenerator(name = "event_sequence", sequenceName = "event_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_sequence")
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name="event_organizers",
            joinColumns = @JoinColumn(name="event_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="app_user_id",referencedColumnName = "id")
    )
    private Set<AppUser> organizers=new HashSet<>();
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime eventDate;
    @NotNull
    private String eventLocation;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt=LocalDateTime.now();
    private boolean deleted=false;

}
