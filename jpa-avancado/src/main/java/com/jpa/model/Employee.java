package com.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	private String dept;

	private int salary;

	public static Employee create(String name, String dep, int salary) {
		// TODO Auto-generated method stub
		Employee emp =  new Employee();
		emp.setName(name);
		emp.setDept(dep);
		emp.setSalary(salary);
		return emp;
	}

}