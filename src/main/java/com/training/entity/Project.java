package com.training.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Project {

    @Id
    @Column(columnDefinition = "DECIMAL(10,0)")
    private BigDecimal projectId;

    @Column(unique=true, length = 30)
    private String projectNm;

    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "dept_id")
    private Department department;

    @Column(length = 1, nullable = false)
    private char difficulty;

    private LocalDate insTm;

    private LocalDate updTm;

    @Column(columnDefinition = "DECIMAL(10,0)")
    private BigDecimal version;

    public BigDecimal getProjectId() {
        return projectId;
    }

    public void setProjectId(BigDecimal projectId) {
        this.projectId = projectId;
    }

    public String getProjectNm() {
        return projectNm;
    }

    public void setProjectNm(String projectNm) {
        this.projectNm = projectNm;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public char getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(char difficulty) {
        this.difficulty = difficulty;
    }

    public LocalDate getInsTm() {
        return insTm;
    }

    public void setInsTm(LocalDate insTm) {
        this.insTm = insTm;
    }

    public LocalDate getUpdTm() {
        return updTm;
    }

    public void setUpdTm(LocalDate updTm) {
        this.updTm = updTm;
    }

    public BigDecimal getVersion() {
        return version;
    }

    public void setVersion(BigDecimal version) {
        this.version = version;
    }
}
