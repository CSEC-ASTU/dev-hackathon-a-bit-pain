package com.yaphet.devhackathonabitpain.repositories;

import com.yaphet.devhackathonabitpain.models.ActivationRequest;
import com.yaphet.devhackathonabitpain.models.AppUser;
import com.yaphet.devhackathonabitpain.utilities.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ActivationRequestRepository extends JpaRepository<ActivationRequest,Long> {
    Optional<List<ActivationRequest>> findByStatus(Status status);

    @Transactional
    @Modifying
    @Query("UPDATE ActivationRequest a SET a.status=?2 WHERE a.appUser.id=?1")
    void updateStatus(Long id,Status status);
}
