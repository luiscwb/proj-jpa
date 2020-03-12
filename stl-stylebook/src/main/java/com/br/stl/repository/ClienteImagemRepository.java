package com.br.stl.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.br.stl.model.entity.ClienteImagem;

public interface ClienteImagemRepository extends CrudRepository<ClienteImagem, Long> {
	
	@Query(value = "Select * from cliente_imagem Where cliente_id = :idCliente", nativeQuery = true)
	public Iterable<ClienteImagem> findClientImages( @Param("idCliente") Long idCliente);
	
	
}

