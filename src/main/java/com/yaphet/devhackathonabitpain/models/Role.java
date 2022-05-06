package com.yaphet.devhackathonabitpain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="app_roles")
public class Role {

    @Id
    @SequenceGenerator(name="role_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="role_sequence")
    private Long id;
    @NotBlank
    private String roleName;
    @NotBlank
    private String roleDescription;
    @NotNull
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="app_role_privileges",
            joinColumns = @JoinColumn(nullable = false,name="app_role_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(nullable = false,name="app_privilege_id",referencedColumnName = "id")
    )
    private Set<Privilege> privileges=new HashSet<>();

    public Role(String roleName,String roleDescription){
        this.roleName=roleName;
        this.roleDescription=roleDescription;

    }



}
