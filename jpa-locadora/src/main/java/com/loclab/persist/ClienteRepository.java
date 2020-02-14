package com.loclab.persist;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.loclab.entity.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	@Query(value = "SELECT * FROM cliente c WHERE c.cpf = :cpf", nativeQuery = true)
	public Cliente findByCpf( @Param("cpf") String cpf);
}
