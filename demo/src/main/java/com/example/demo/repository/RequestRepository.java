package com.example.demo.repository;

import com.example.demo.model.Request;
import com.example.demo.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    // Used by the background scheduler to find past-due requests
    List<Request> findByStatusNotAndSlaExpiryTimeBeforeAndEscalatedFalse(Status status, LocalDateTime time);
}