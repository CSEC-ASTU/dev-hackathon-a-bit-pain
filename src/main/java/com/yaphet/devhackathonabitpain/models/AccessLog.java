package com.yaphet.devhackathonabitpain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private String activity;
    private LocalDateTime timeStamp;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="app_user_id",referencedColumnName = "id")
    private AppUser user;
}
