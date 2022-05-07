package com.yaphet.devhackathonabitpain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="app_users")
public class AccessLog {

    @Id
    @SequenceGenerator(name = "log_sequence", sequenceName = "log_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_sequence")
    private Long id;
    @NotBlank
    private String activity;

    private LocalDateTime timeStamp=LocalDateTime.now();
//    @NotNull
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="app_user_id",referencedColumnName = "id")
    private AppUser user;
}
