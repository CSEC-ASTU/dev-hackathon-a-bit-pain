package com.yaphet.devhackathonabitpain.repositories;

import com.yaphet.devhackathonabitpain.models.AppUser;
import com.yaphet.devhackathonabitpain.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Query("SELECT a FROM AppUser a WHERE  a.enabled=TRUE AND a.locked=false AND a.deleted=FALSE ORDER BY a.firstName,a.lastName")
    List<Event> findAllUnDeleted();
    Optional<AppUser> findByEmail(String email);


    @Transactional
    @Modifying
    @Query("UPDATE AppUser a SET a.enabled=TRUE WHERE a.email=?1")
    void enableAppUser(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a SET a.locked=FALSE WHERE a.email=?1")
    void unlockAppUser(String email);

}
