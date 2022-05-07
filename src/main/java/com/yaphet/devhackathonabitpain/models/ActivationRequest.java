package com.yaphet.devhackathonabitpain.models;

import com.yaphet.devhackathonabitpain.utilities.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="activation_requests")
public class ActivationRequest {
    @Id
    @SequenceGenerator(name = "activation_sequence", sequenceName = "activation_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activation_sequence")
    private Long id;
//    @NotNull
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="app_user_id",referencedColumnName = "id")
    private AppUser appUser;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime requestedAt=LocalDateTime.now();
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status=Status.PENDING;
}
