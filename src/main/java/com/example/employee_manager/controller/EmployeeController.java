package com.example.employee_manager.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.employee_manager.entity.Employee;
import com.example.employee_manager.repository.DepartmentRepository;
import com.example.employee_manager.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
	
	private final EmployeeService service;
	private final DepartmentRepository departmentRepository;
	
	// 社員一覧画面を表示
	@GetMapping("/employees")
	public String listEmployees(Model model, 
			@RequestParam(name = "keyword", required = false) String keyword) {
		List<Employee> list;
		if(keyword != null && !keyword.isEmpty()) {
			list = service.search(keyword);
		} else {
			list = service.findAll();
		}
		
		model.addAttribute("employees", list);
		model.addAttribute("keyword", keyword);
		
		return "employees";
	}
	
	// 社員登録画面を表示
	@GetMapping("/employees/new")
	public String createEmployeeForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		
		// 部署の選択肢を画面に渡す
		model.addAttribute("departments", departmentRepository.findAll());
		
		return "create_employee";
	}
	
	// 登録ボタンが押されたときの処理
	@PostMapping("/employees")
	public String saveEmployee(@Validated @ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			// エラーで戻る時も、部署リストを渡さないとプルダウンが作れなくてエラーになる
			model.addAttribute("departments", departmentRepository.findAll());
			return "create_employee";
		}
		
		service.save(employee);
		return "redirect:/employees";
	}
	
	// 編集画面
	@GetMapping("/employees/edit/{id}")
	public String editEmployeeFrom(@PathVariable("id") Integer id, Model model) {
		Employee employee = service.get(id);
		model.addAttribute("employee", employee);
		
		// 編集画面でも部署を選べるようにする
		model.addAttribute("departments", departmentRepository.findAll());
		
		return "create_employee";
	}
	
	// 削除機能
	@GetMapping("/employees/delete/{id}")
	public String deleteEmployee(@PathVariable("id") Integer id) {
		service.delete(id);
		return "redirect:/employees";
	}
}