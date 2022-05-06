package com.yaphet.devhackathonabitpain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="dev_projects")
public class Project {

    @Id
    @SequenceGenerator(name = "project_sequence", sequenceName = "project_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_sequence")
    private Long id;
    @NotBlank
    private String title;
    @NotNull
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name="project_authors",
            joinColumns = @JoinColumn(name="project_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="app_user_id",referencedColumnName = "id")
    )
    private Set<AppUser> authors;
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name="project_attachments",
            joinColumns = @JoinColumn(name="project_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="attachment_id",referencedColumnName = "id")
    )
    private Set<Attachment> attachments;
    private String githubLink;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createAt=LocalDateTime.now();
}
