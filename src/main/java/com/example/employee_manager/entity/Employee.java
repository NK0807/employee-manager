package com.example.employee_manager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "名前を入力してください")
	private String name;		//名前
	
	@NotBlank(message = "部署を入力してください")
	private String department;	//部署
	
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "メールアドレスの形式で入力してください")
	private String email;		//メール
}
