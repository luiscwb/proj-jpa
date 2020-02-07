package com.jpa.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.employee.model.Employee;
import com.jpa.employee.repo.EmployeeRepository;

@Service
public class EmpolyeeService {

	@Autowired
	private EmployeeRepository employeeRepo;

	public List<Employee> findEmployees() {
		return employeeRepo.findAllUsers();
	}

	@Transactional
	public Employee saveEmployee(Employee emp) {

		//Para salvar os skills deve ser setado em cada skill o employee que sera salvo
		emp.getSkills().forEach(e -> e.setEmployee(emp) );
		employeeRepo.save(emp);
		
		return emp;
	}


	//sem sentido
	public Employee updateEmployee(Employee emp) {
		Employee ret = null;
		Optional<Employee> dbemp = employeeRepo.findById(emp.getId());
		if (dbemp.isPresent()) {
			dbemp.get().setSkills(null);
			employeeRepo.save(dbemp.get());
			ret = dbemp.get();
		}
		return ret;
	}
		

	
	
	// Aapga os registros de employee e em cascata os correspondentes de skill
	@Transactional
	public void deleteEmployee(Long id) {
		Optional<Employee> emp = employeeRepo.findById(id);
		if (emp.isPresent() ) 
			employeeRepo.delete(emp.get());
	}

	public List<Employee> findAllWithParams(String name, Integer salary) {
		List<Employee> result = new ArrayList<>();
		result = employeeRepo.findAllWithParams(name, salary);
		return result;
	}

	public Page<Employee> findAllWithPageble(Integer numpagina) {

		Pageable pageable = PageRequest.of(numpagina, 5);
		Page<Employee> result = employeeRepo.findAllWithPageble(pageable);

//		int totalPages = result.getTotalPages();
//		long totalElements = result.getTotalElements();
		
		
		return result;
		
	}

}
