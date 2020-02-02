package com.jpa.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.jpa.model.Employee;

@Repository
public class EmployeeAdavancedRepository {

    @PersistenceContext
    private EntityManager em;
    
    public void saveWithEntityManager() {
    	em.getTransaction().begin();
    	em.persist(Employee.create("Luis", "IT", 5000));
    	em.getTransaction().commit();
    	
    }
    
}
