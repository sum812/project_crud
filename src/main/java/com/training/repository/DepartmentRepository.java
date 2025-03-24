package com.training.repository;

import com.training.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, BigDecimal> {

}
