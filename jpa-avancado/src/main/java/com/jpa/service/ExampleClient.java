package com.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.model.Employee;
import com.jpa.repo.EmployeeRepository;

@Component
public class ExampleClient {

  @Autowired
  private EmployeeRepository repo;

  public List<Employee> findEmployees() {
	  return repo.findAllActiveUsers();
  }

  @Transactional
  public Employee saveEmployees() {
      repo.save(Employee.create("Mike", "Sale", 1000));
      repo.save(Employee.create("Diana", "Admin", 3000));
      repo.save(Employee.create("Diana", "IT", 3200));
      return Employee.create("Diana", "IT", 3200);
  }
  
 
}