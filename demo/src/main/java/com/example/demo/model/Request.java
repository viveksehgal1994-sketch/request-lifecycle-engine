package com.example.demo.model;

import com.example.demo.model.enums.Priority;
import com.example.demo.model.enums.Status;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    private boolean sensitivityFlag;
    private String assignedOperatorId;
    private String requiredSkill;
    
    // SLA Tracking
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime slaExpiryTime;
    private boolean escalated;

    // Constructors
    public Request() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public boolean isSensitivityFlag() { return sensitivityFlag; }
    public void setSensitivityFlag(boolean sensitivityFlag) { this.sensitivityFlag = sensitivityFlag; }

    public String getAssignedOperatorId() { return assignedOperatorId; }
    public void setAssignedOperatorId(String assignedOperatorId) { this.assignedOperatorId = assignedOperatorId; }

    public String getRequiredSkill() { return requiredSkill; }
    public void setRequiredSkill(String requiredSkill) { this.requiredSkill = requiredSkill; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public LocalDateTime getSlaExpiryTime() { return slaExpiryTime; }
    public void setSlaExpiryTime(LocalDateTime slaExpiryTime) { this.slaExpiryTime = slaExpiryTime; }

    public boolean isEscalated() { return escalated; }
    public void setEscalated(boolean escalated) { this.escalated = escalated; }
}