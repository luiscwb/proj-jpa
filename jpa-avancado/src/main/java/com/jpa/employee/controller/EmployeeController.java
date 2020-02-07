package com.jpa.employee.controller;

import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.employee.model.Employee;
import com.jpa.employee.service.EmpolyeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmpolyeeService service;
	
	
	@PostMapping("/update")
	public Employee create(@RequestBody Employee emp) {
		Employee ret = Employee.create("Caso de erro", "erro", 0);
		try {
			return service.saveEmployee(emp);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	
	@DeleteMapping("/delete")
	public void delete(@RequestBody Employee emp) {
		service.deleteEmployee(emp.getId());
	}
	
	@GetMapping("/find")
	public List<Employee> findEmployees() {
		return service.findEmployees();
	}

	@GetMapping("/findaparams")
	public List<Employee> findAllWithParams(@Param("nome") String nome, @Param("salario") Integer salario) {
		return service.findAllWithParams(nome, salario);
	}
	
	@GetMapping("/findapaginacao")
	public Page<Employee> findAllWithPageble(@Param("numpagina") Integer numpagina) {
		return service.findAllWithPageble(numpagina);
	}

}
