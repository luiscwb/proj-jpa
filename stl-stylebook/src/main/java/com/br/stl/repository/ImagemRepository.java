package com.br.stl.repository;

import org.springframework.data.repository.CrudRepository;

import com.br.stl.model.entity.ClienteImagem;

public interface ImagemRepository extends CrudRepository<ClienteImagem, Long> {
	
}
