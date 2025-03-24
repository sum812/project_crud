package com.training.controller;

import com.training.entity.Project;
import com.training.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private IProjectService projectService;

    @GetMapping
    public String homePage(
            @RequestParam(name = "projectId", required = false)Optional<BigDecimal> projectId,
            @RequestParam(name = "projectName", required = false) Optional<String> projectName,
            @RequestParam(name = "difficulty", required = false) Optional<Character> difficulty,
            @RequestParam(name = "deptId", required = false) Optional<BigDecimal> deptId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            Model model
            ) {
        Page<Project> projectPage = projectService.searchProjects(
                projectId.orElse(null),
                projectName.orElse(null),
                difficulty.orElse(null),
                deptId.orElse(null),
                PageRequest.of(page, 5)
        );

        model.addAttribute("projects", projectPage.getContent());
        model.addAttribute("totalPages", projectPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "home";
    }
}
