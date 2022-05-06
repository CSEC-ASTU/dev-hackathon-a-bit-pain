package com.yaphet.devhackathonabitpain.models;

import com.yaphet.devhackathonabitpain.utilities.enums.Scope;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
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
    @NotBlank(message = "Title field required")
    private String title;
    @NotBlank(message = "Description field required")
    private String description;
    @NotNull
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name="event_organizers",
            joinColumns = @JoinColumn(name="app_user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="app_role_id",referencedColumnName = "id")
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
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name="event_attendees",
            joinColumns = @JoinColumn(name="app_user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="app_role_id",referencedColumnName = "id")
    )
    private Set<AppUser> attendees=new HashSet<>();
    @NotNull
    private Scope scope;

    public Event(String title, String description, Set<AppUser> organizers, LocalDateTime eventDate, String eventLocation,Scope scope) {
        this.title = title;
        this.description = description;
        this.organizers = organizers;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.scope=scope;
    }
}
