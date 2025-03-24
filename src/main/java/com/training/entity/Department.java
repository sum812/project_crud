package com.training.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Department {

    @Id
    @Column(columnDefinition = "DECIMAL(10,0)", name = "dept_id")
    private BigDecimal deptId;

    @Column(length = 20)
    private String deptNm;

    public BigDecimal getDeptId() {
        return deptId;
    }

    @OneToMany(mappedBy = "department", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Project> projects;

    public void setDeptId(BigDecimal deptId) {
        this.deptId = deptId;
    }

    public String getDeptNm() {
        return deptNm;
    }

    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
