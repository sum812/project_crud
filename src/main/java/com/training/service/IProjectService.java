package com.training.service;

import com.training.dto.ProjectDTO;
import com.training.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface IProjectService {

    boolean isProjectIdExists(BigDecimal id);

    void saveProject(ProjectDTO projectDTO);

    Page<Project> searchProjects(BigDecimal projectId, String projectName, Character difficulty, BigDecimal deptId, Pageable pageable);
}
