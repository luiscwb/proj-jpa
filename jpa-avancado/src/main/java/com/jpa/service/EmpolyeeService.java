package com.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.model.Employee;
import com.jpa.repo.EmployeeRepository;
import com.jpa.repo.SkillRepository;

@Service
public class EmpolyeeService {

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private SkillRepository skillRepo;

	public List<Employee> findEmployees() {
		return employeeRepo.findAllActiveUsers();
	}

	@Transactional
	public Employee saveEmployee(Employee emp) {

		emp.getSkills().forEach(e -> skillRepo.save(e));
		employeeRepo.save(emp);
		return emp;
	}

	public void deleteEmployee(Employee emp) {
		employeeRepo.delete(emp);
	}

	public List<Employee> findAllWithParams(String name, Integer salary) {
		List<Employee> result = new ArrayList<>();
		result = employeeRepo.findAllWithParams(name, salary);
		return result;
	}

	public Page<Employee> findAllWithPageble(Integer numpagina) {

		Pageable pageable = PageRequest.of(numpagina, 5);
		Page<Employee> result = employeeRepo.findAllWithPageble(pageable);

		int totalPages = result.getTotalPages();
		long totalElements = result.getTotalElements();
		
		
		return result;
		
	}

}
