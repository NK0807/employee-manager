package com.example.employee_manager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.employee_manager.entity.Employee;
import com.example.employee_manager.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	
	private final EmployeeRepository repository;
	
	// 全体取得
	public List<Employee> findAll() {
		return repository.findAll();
	}
	
	// 保存 (新規・更新)
	public void save(Employee employee) {
		repository.save(employee);
	}
	
	// 1件取得
	public Employee get(Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	// 削除
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	// 名前で検索
	public List<Employee> search(String keyword) {
		return repository.findByNameContaining(keyword);
	}
}
