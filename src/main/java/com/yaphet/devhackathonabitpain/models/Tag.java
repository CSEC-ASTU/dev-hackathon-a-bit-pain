package com.yaphet.devhackathonabitpain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tags")
public class Tag {
    @Id
    @SequenceGenerator(name = "tag_sequence", sequenceName = "tag_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_sequence")
    private Long id;
    @NotBlank
    private String tagName;

}
