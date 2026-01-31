package com.example.employee_manager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.employee_manager.entity.Department;
import com.example.employee_manager.entity.User;
import com.example.employee_manager.repository.DepartmentRepository;
import com.example.employee_manager.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner{
	private final DepartmentRepository departmentRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
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
		
		// ユーザー登録処理
		if(userRepository.count() == 0) {
			// 管理者(ADMIN)
			User admin = new User();
			admin.setUsername("admin");
			// パスワードは"password"を暗号化して保存
			admin.setPassword(passwordEncoder.encode("password"));
			admin.setRole("ADMIN");
			userRepository.save(admin);
			
			// 一般ユーザー(USER)
			User general = new User();
			general.setUsername("user");
			general.setPassword(passwordEncoder.encode("password"));
			general.setRole("USER");
			userRepository.save(general);
			
			System.out.println("初期データ(ユーザー: admin & user)を登録しました！");
		}
	}
	
	private void createDepartment(String name) {
		Department d = new Department();
		d.setName(name);
		departmentRepository.save(d);
	}
}
