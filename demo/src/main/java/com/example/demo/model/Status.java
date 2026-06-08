package com.example.demo.model.enums;

public enum Status {
    CREATED,
    INCOMPLETE, // For "Validate req. -> Incomplete send back"
    ASSIGNED,
    IN_PROGRESS,
    PENDING_APPROVAL, // For "Review and approve"
    COMPLETED
}