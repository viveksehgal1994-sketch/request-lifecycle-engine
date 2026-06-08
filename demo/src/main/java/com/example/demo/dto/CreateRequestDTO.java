package com.example.demo.dto;

import com.example.demo.model.enums.Priority;
import jakarta.validation.constraints.NotNull;

public class CreateRequestDTO {
    private String description; // Leaving it optional to simulate "Incomplete" validation rule
    
    @NotNull(message = "Priority is required")
    private Priority priority;
    
    private boolean sensitivityFlag;
    private String requiredSkill;

    // Getters and Setters
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }

    public boolean isSensitivityFlag() { return sensitivityFlag; }
    public void setSensitivityFlag(boolean sensitivityFlag) { this.sensitivityFlag = sensitivityFlag; }

    public String getRequiredSkill() { return requiredSkill; }
    public void setRequiredSkill(String requiredSkill) { this.requiredSkill = requiredSkill; }
}