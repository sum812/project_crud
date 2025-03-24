package com.training.controller;

import com.training.dto.ProjectDTO;
import com.training.service.IDepartmentService;
import com.training.service.IProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "redirect:/home?success";
    }
}
