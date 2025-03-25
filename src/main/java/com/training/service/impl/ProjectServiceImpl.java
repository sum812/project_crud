package com.training.service.impl;

import com.training.dto.ProjectDTO;
import com.training.entity.Project;
import com.training.repository.ProjectRepository;
import com.training.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;

@Service
public class ProjectServiceImpl implements IProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project getProjectById(BigDecimal id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project không tồn tại!"));
    }

    @Override
    public boolean isProjectIdExists(BigDecimal id) {
        return projectRepository.existsById(id);
    }

    @Override
    public void saveProject(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setProjectId(projectDTO.getProjectId());
        project.setProjectNm(projectDTO.getProjectName());
        project.setDifficulty(projectDTO.getDifficulty());
        project.setDepartment(projectDTO.getDepartment());
        project.setVersion(new BigDecimal(1));
        project.setInsTm(LocalDate.now());
        project.setUpdTm(LocalDate.now());
        projectRepository.save(project);
    }

    @Override
    public void deleteProjectById(BigDecimal id) {
        projectRepository.deleteById(id);
    }

    @Override
    public void updateProject(ProjectDTO projectDTO) {
        Project existingProject = projectRepository.findById(projectDTO.getProjectId()).orElseThrow(() -> new RuntimeException("Project không tồn tại!"));

        existingProject.setProjectNm(projectDTO.getProjectName());
        existingProject.setDifficulty(projectDTO.getDifficulty());
        existingProject.setDepartment(projectDTO.getDepartment());
        existingProject.setVersion(existingProject.getVersion().plus(new MathContext(1)));
        existingProject.setUpdTm(LocalDate.now());

        projectRepository.save(existingProject);
    }

    @Override
    public Page<Project> searchProjects(BigDecimal projectId, String projectName, Character difficulty, BigDecimal deptId, Pageable pageable) {
        return projectRepository.searchProjects(projectId, projectName, difficulty, deptId, pageable);
    }
}
