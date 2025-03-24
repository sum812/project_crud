package com.training.repository;

import com.training.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProjectRepository extends JpaRepository<Project, BigDecimal> {

    @Query("""
                SELECT p FROM Project p
                WHERE (:projectId IS NULL OR p.projectId = :projectId)
                AND (:projectName IS NULL OR LOWER(p.projectNm) LIKE LOWER(CONCAT('%', :projectName, '%')))
                AND (:deptId IS NULL OR p.department.deptId = :deptId)
                AND (:difficulty IS NULL OR p.difficulty = :difficulty)
            """)
    Page<Project> searchProjects(
            @Param("projectId") BigDecimal projectId,
            @Param("projectName") String projectName,
            @Param("difficulty") Character difficulty,
            @Param("deptId") BigDecimal deptId,
            Pageable pageable
    );

}
