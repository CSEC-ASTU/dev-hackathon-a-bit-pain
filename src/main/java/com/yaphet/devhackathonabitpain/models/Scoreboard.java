package com.yaphet.devhackathonabitpain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="dev_projects")
public class Scoreboard {
    @Id
    @SequenceGenerator(name = "attachment_sequence", sequenceName = "attachment_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attachment_sequence")
    private Long id;
    private String description;
//    @NotNull
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name="scoreboard_attachments",
            joinColumns = @JoinColumn(name="scoreboard_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="attachment_id",referencedColumnName = "id")
    )
    private Set<Attachment> attachments;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate contestDate;
}
