package com.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.model.Employee;
import com.jpa.repo.EmployeeRepository;

@Service
public class EmpolyeeService {

	@Autowired
	private EmployeeRepository repo;

	public List<Employee> findEmployees() {
		return repo.findAllActiveUsers();
	}

	@Transactional
	public Employee saveEmployees() {
		for (int i = 0; i < 50; i++) {
			repo.save(Employee.create("Mike"+i, "Sale", 1000));
			repo.save(Employee.create("Diana"+i, "Admin", 3000));
			repo.save(Employee.create("Maria"+i, "IT", 3200));			
		}
		return Employee.create("Diana", "IT", 3200);
	}

}