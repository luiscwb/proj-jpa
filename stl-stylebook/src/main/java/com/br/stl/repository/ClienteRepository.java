package com.br.stl.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.br.stl.model.entity.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	
	@Query(value = "Select * from cliente c Where c.cpf = :cpf", nativeQuery = true)
	public Cliente findByCpf( @Param("cpf") String cpf );
	
	
	@Modifying
	@Query(value = "Delete from cliente c Where c.cpf = :cpf", nativeQuery = true)
	public Cliente deleteByCpf( @Param("cpf") String cpf );
	
}
