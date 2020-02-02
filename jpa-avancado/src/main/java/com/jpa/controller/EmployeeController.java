package com.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.model.Employee;
import com.jpa.service.ExampleClient;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	ExampleClient service;
	
	@GetMapping("/create")
	public Employee create(Employee emp) {
		return service.saveEmployees();
	}
	
	@GetMapping("/find")
	public List<Employee> findEmployees() {
		return service.findEmployees();
	}
	
}
