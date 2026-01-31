package com.example.employee_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee_manager.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

}
