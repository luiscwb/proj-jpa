package com.br.stl.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.br.stl.model.entity.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	
	@Query(value = "SELECT nomeCompleto, cpf, telefone FROM cliente", nativeQuery = true)
	public Iterable<Cliente> findAll();
	
}
