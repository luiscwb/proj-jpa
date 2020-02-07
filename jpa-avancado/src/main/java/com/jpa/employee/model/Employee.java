package com.jpa.employee.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	//@Column(name="name", columnDefinition = "varchar(50)", unique = true)
	private String name;

	@Column(nullable = false)
	private String dept;

	private int salary;

	//Relacionamento biderecional com Skill usando campo employee dessa classe
	//Obrigatorio usar cascade para replicar o save nos relacionamentos
	@OneToMany(
			fetch = FetchType.LAZY, 
			mappedBy = "employee", 
			cascade=CascadeType.ALL
	) 
	private List<Skill> skills;
	
	
	
	//Departament
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Departament> departaments;
	
	
	public List<Skill> getSkills() {
		if (skills == null)
			return new ArrayList<>();
		else
			return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

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

	public static Employee create(String name, String dep, int salary) {
		Employee emp =  new Employee();
		emp.setName(name);
		emp.setDept(dep);
		emp.setSalary(salary);
		return emp;
	}
	
	public void  copy(Employee empFrom) {
		this.setDept(empFrom.getDept());
		this.setId(empFrom.getId());
		this.setName(empFrom.getName());
		this.setSalary(empFrom.getSalary());
		this.setSkills(empFrom.getSkills());
	}

}