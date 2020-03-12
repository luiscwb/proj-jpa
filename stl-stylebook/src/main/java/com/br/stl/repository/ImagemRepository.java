package com.br.stl.repository;

import org.springframework.data.repository.CrudRepository;

import com.br.stl.model.entity.Imagem;

public interface ImagemRepository extends CrudRepository<Imagem, Long> {
	
}
