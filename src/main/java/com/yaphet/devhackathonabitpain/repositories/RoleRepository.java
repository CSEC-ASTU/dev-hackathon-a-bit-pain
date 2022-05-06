package com.yaphet.devhackathonabitpain.repositories;

import com.yaphet.devhackathonabitpain.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
//    @Query("SELECT r.id,r.roleName,r.roleDescription FROM Role r WHERE r.roleName=?1")
    Optional<Role> findByRoleName(String roleName);
}
