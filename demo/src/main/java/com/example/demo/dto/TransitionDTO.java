package com.example.demo.dto;

import com.example.demo.model.enums.Status;

public class TransitionDTO {
    private Status status;
    private String operatorId;

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public String getOperatorId() { return operatorId; }
    public void setOperatorId(String operatorId) { this.operatorId = operatorId; }
}