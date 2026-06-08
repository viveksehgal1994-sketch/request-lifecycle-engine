package com.example.demo.service;

import com.example.demo.dto.CreateRequestDTO;
import com.example.demo.model.Request;
import com.example.demo.model.enums.Priority;
import com.example.demo.model.enums.Status;
import com.example.demo.repository.RequestRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RequestService {

    private final RequestRepository repository;

    public RequestService(RequestRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Request createRequest(CreateRequestDTO dto) {
        Request request = new Request();
        request.setPriority(dto.getPriority());
        request.setSensitivityFlag(dto.isSensitivityFlag());
        request.setRequiredSkill(dto.getRequiredSkill());
        request.setCreatedAt(LocalDateTime.now());
        request.setUpdatedAt(LocalDateTime.now());
        request.setEscalated(false);

        // Validation Rule: If description is missing/empty -> Mark INCOMPLETE
        if (dto.getDescription() == null || dto.getDescription().trim().isEmpty()) {
            request.setStatus(Status.INCOMPLETE);
            request.setDescription("[SYSTEM: Missing Details]");
            System.out.println("LOG: Request flagged incomplete. Sent back to queue.");
        } else {
            request.setStatus(Status.CREATED);
            request.setDescription(dto.getDescription());
        }

        // Assign SLA duration based on Priority
        int minutesToResolve = dto.getPriority() == Priority.HIGH ? 2 : 5; // Simulating short SLAs for demo tracking
        request.setSlaExpiryTime(LocalDateTime.now().plusMinutes(minutesToResolve));

        return repository.save(request);
    }

    @Transactional
    public Request updateLifecycle(Long id, Status targetStatus, String operatorId) {
        Request request = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found with id: " + id));

        // State Machine Rule Checks
        if (targetStatus == Status.ASSIGNED) {
            request.setAssignedOperatorId(operatorId);
            System.out.println("LOG: Notifying operator " + operatorId + " of assignment.");
        }

        if (targetStatus == Status.COMPLETED) {
            // High priority logic requirement check
            if (request.getPriority() == Priority.HIGH && request.getStatus() != Status.PENDING_APPROVAL) {
                request.setStatus(Status.PENDING_APPROVAL);
                request.setUpdatedAt(LocalDateTime.now());
                System.out.println("LOG: High priority item requires an additional review step before completion.");
                return repository.save(request);
            }
            
            // Once completed, notify requestor rule
            System.out.println("LOG: NOTIFICATION SENT: Requestor notified that request #" + id + " is completed.");
        }

        request.setStatus(targetStatus);
        request.setUpdatedAt(LocalDateTime.now());
        return repository.save(request);
    }

    // SLA Timer Background Worker Execution (Runs every 30 seconds)
    @Scheduled(fixedRate = 30000)
    @Transactional
    public void processSlaEscalations() {
        List<Request> overdueRequests = repository.findByStatusNotAndSlaExpiryTimeBeforeAndEscalatedFalse(
                Status.COMPLETED, LocalDateTime.now()
        );

        for (Request req : overdueRequests) {
            req.setEscalated(true);
            req.setPriority(Priority.HIGH); // Auto-escalate priority setting
            repository.save(req);
            System.out.println("ALERT: SLA Timer Expired for Request #" + req.getId() + ". Escalating ticket workflow.");
        }
    }
}