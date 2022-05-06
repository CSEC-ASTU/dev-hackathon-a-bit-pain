package com.yaphet.devhackathonabitpain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="posts")
public class Post {

    @Id
    @SequenceGenerator(name = "post_sequence", sequenceName = "post_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_sequence")
    private Long id;
    private String title;
    private String description;
    @OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name="app_user_id",referencedColumnName = "id")
    private AppUser createdBy;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt=LocalDateTime.now();
    @OneToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="post_tags",
            joinColumns = @JoinColumn(nullable = false,name="post_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(nullable = false,name="tag_id",referencedColumnName = "id")
    )
    private Set<Tag> tags=new HashSet<>();
    private boolean deleted=false;
}
