package com.jpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

  @Transactional(timeout = 10)
  @Override
  <S extends Employee> S save(S s);
  
  @Query(value = "SELECT * FROM employee u WHERE u.salary = 1000", nativeQuery = true)
  List<Employee> findAllActiveUsers();
}
