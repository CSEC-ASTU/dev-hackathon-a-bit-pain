package com.yaphet.devhackathonabitpain.repositories;

import com.yaphet.devhackathonabitpain.models.Event;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {

    @Query("SELECT e FROM Event e WHERE e.deleted=false ORDER BY e.createdAt")
    List<Event> findAllUnDeleted();

    @Query("UPDATE Event SET deleted=true WHERE id=?1")
    void deleteEventsById(Long id);
}
