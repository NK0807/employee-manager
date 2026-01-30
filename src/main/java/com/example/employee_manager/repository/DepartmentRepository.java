package com.example.employee_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee_manager.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
