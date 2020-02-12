//https://www.baeldung.com/spring-data-jpa-query
//https://www.baeldung.com/spring-data-jpa-pagination-sorting
//https://dzone.com/articles/pagination-and-sorting-with-spring-data-jpa


//String jpql = "select c from Conta c join fetch c.movimentacoes";
// Suprimindo comportamento lazy, (n+1), excuta o select com join apenas 1 vez

package com.jpa.employee.repo;

import java.util.List;

import javax.persistence.FetchType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.employee.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

  @Transactional(timeout = 10)
  @Override
  <S extends Employee> S save(S s);
  
  //Select costumizado
  // Caso seja necessario retornas os skill para cada employee usar FETCH para executar apenas um select
  // senao usar FETCH, sera relilizado um select por cada registro de employee para recuperar seus skills
  // o LEFT deixa como obrigatorio os registros do employee
  @Query(value = "SELECT e FROM Employee e LEFT JOIN FETCH e.skills s" )
  List<Employee> findAllUsers();
  
  
  //Usando paginacao do resultado apenas para jpsql, para nativo é diferente
  @Query(value = "SELECT e FROM Employee e order by id")
  Page<Employee> findAllWithPageble(Pageable pageable);

  
  //Passando parametros com jpql
  @Query("SELECT e FROM Employee e WHERE e.name = :pname and e.salary = :psalary")
  List<Employee> findAllWithParams(
		  @Param("pname") String name,
		  @Param("psalary") Integer salary
	);
   
}
