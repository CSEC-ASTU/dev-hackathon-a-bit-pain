package com.yaphet.devhackathonabitpain.repositories;

import com.yaphet.devhackathonabitpain.models.ActivationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivationRequestRepository extends JpaRepository<ActivationRequest,Long> {
}
