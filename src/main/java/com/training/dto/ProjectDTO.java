package com.training.dto;

import com.training.entity.Department;

import java.math.BigDecimal;

public class ProjectDTO {

    private BigDecimal projectId;

    private String projectName;

    private Character difficulty;

    private Department department;

    public BigDecimal getProjectId() {
        return projectId;
    }

    public void setProjectId(BigDecimal projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Character getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Character difficulty) {
        this.difficulty = difficulty;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
