package com.yaphet.devhackathonabitpain.models;

import com.yaphet.devhackathonabitpain.utilities.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="app_users")
public class AppUser {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String userName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dob;
    @NotNull
    private Gender gender;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="app_user_roles",
            joinColumns = @JoinColumn(nullable = false,name="app_user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(nullable = false,name="app_role_id",referencedColumnName = "id")
    )
    private Set<Role> roles=new HashSet<>();
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt=LocalDateTime.now();
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="division_members",
            joinColumns = @JoinColumn(nullable = false,name="app_user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(nullable = false,name="division_id",referencedColumnName = "id")
    )
    private Set<Division> divisions=new HashSet<>();
    private boolean deleted=false;
    private Boolean enabled = false;
    private Boolean locked = false;

    public AppUser(String firstName, String lastName, String email, String password, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }

    public String getUserName() {
        return email;
    }

    public void setUserName(String email) {
        this.userName = email;
    }
}
