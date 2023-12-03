package dev.kaushar.authservice.authservice.repositories;


import dev.kaushar.authservice.authservice.models.Session;
import dev.kaushar.authservice.authservice.models.SessionStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Session save(Session session);

    Optional<Session> findById(Long Id);

    Optional<Session> findByTokenAndUser_Id(String token, Long userId);

    @Transactional
    @Modifying
    @Query(value = Queries.UpdateSessionById,nativeQuery = true)
    void updateStatusById(SessionStatus sessionStatus, Long Id);

}
