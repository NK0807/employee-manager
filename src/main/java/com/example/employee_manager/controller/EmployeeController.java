package com.example.employee_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.employee_manager.entity.Employee;
import com.example.employee_manager.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
	
	private final EmployeeService service;
	
	// 社員一覧画面を表示
	@GetMapping("/employees")
	public String listEmployees(Model model) {
		model.addAttribute("employees", service.findAll());
		return "employees";
	}
	
	// 社員登録画面を表示
	@GetMapping("/employees/new")
	public String createEmployeeForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute(employee);
		return "create_employee";
	}
	
	// 登録ボタンが押されたときの処理
	@PostMapping("/employees")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		service.save(employee);
		return "redirect:/employees";
	}
	
	// 編集画面
	@GetMapping("/employees/edit/{id}")
	public String editEmployeeFrom(@PathVariable("id") Integer id, Model model) {
		Employee employee = service.get(id);
		model.addAttribute("employee", employee);
		return "create_employee";
	}
	
	// 削除機能
	@GetMapping("/employees/delete/{id}")
	public String deleteEmployee(@PathVariable("id") Integer id) {
		service.delete(id);
		return "redirect:/employees";
	}
}
