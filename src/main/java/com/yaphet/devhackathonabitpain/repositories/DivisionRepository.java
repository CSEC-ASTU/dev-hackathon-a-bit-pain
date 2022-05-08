package com.yaphet.devhackathonabitpain.repositories;

import com.yaphet.devhackathonabitpain.models.Division;
import com.yaphet.devhackathonabitpain.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DivisionRepository extends JpaRepository<Division,Long> {

    @Query("SELECT d FROM Division d WHERE d.deleted=false ORDER BY d.divisionName")
    List<Division> findAllUnDeleted();
    Optional<Division> findByDivisionName(String divisionName);
}
