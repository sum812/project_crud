package com.training.controller;

import com.training.dto.ProjectDTO;
import com.training.entity.Department;
import com.training.entity.Project;
import com.training.service.IDepartmentService;
import com.training.service.IProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IDepartmentService departmentService;

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "/project/register";
    }

    @PostMapping("/register")
    public String registerProject(@Valid ProjectDTO projectDTO, BindingResult bindingResult, Model model) {
        if (projectService.isProjectIdExists(projectDTO.getProjectId())) {
            bindingResult.rejectValue("projectId", "error.projectId", "ID này đã tồn tại");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "/project/register";
        }

        projectService.saveProject(projectDTO);
        return "redirect:/home?registerSuccess";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable BigDecimal id) {
        projectService.deleteProjectById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable BigDecimal id, Model model) {
        Project project = projectService.getProjectById(id);
        List<Department> departments = departmentService.getAllDepartments();

        model.addAttribute("project", project);
        model.addAttribute("departments", departments);
        return "project/edit";
    }

    @PostMapping("/edit")
    public String updateProject(@ModelAttribute ProjectDTO projectDTO, RedirectAttributes redirectAttributes) {
        try {
            projectService.updateProject(projectDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Cập nhật thất bại!");
        }
        return "redirect:/home";
    }
}
