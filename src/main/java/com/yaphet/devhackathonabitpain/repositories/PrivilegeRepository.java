package com.yaphet.devhackathonabitpain.repositories;

import com.yaphet.devhackathonabitpain.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege,Long> {
    Optional<Privilege> findByPrivilegeName(String privilegeName);
}
