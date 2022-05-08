package com.yaphet.devhackathonabitpain.repositories;

import com.yaphet.devhackathonabitpain.models.Event;
import com.yaphet.devhackathonabitpain.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByRoleName(String roleName);
}
