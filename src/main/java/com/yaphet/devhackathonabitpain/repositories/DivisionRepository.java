package com.yaphet.devhackathonabitpain.repositories;

import com.yaphet.devhackathonabitpain.models.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisionRepository extends JpaRepository<Division,Long> {
}
