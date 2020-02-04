package com.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.model.Employee;
import com.jpa.service.EmpolyeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmpolyeeService service;
	
	@GetMapping("/create")
	public Employee create(Employee emp) {
		Employee ret = Employee.create("Caso de erro", "erro", 0);
		try {
			return service.saveEmployees();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	@GetMapping("/find")
	public List<Employee> findEmployees() {
		return service.findEmployees();
	}
	
}
