package com.example.employee_manager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.employee_manager.entity.Department;
import com.example.employee_manager.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner{
	private final DepartmentRepository departmentRepository;
	
	@Override
	public void run(String... args) throws Exception{
		// もし部署テーブルが空っぽなら
		if(departmentRepository.count() == 0) {
			
			// 部署データを作る
			createDepartment("営業部");
			createDepartment("開発部");
            createDepartment("経理部");
            createDepartment("人事部");
            
            System.out.println("初期データ(部署)を登録しました！");
		}
	}
	
	private void createDepartment(String name) {
		Department d = new Department();
		d.setName(name);
		departmentRepository.save(d);
	}
}
