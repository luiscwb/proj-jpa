package com.br.stl.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.br.stl.model.entity.Caracteristica;

public interface CaracteristicaRepository extends CrudRepository<Caracteristica, Long>  {

	@Modifying
	@Query(value = "Insert Into caracteristica_imagens (caracteristica_id, imagens_id) values (:idCaracteristica, :idImagem)", nativeQuery = true)
	public void addImageForCaracteristica( @Param("idCaracteristica") Long idCaracteristica, @Param("idImagem") Long idImagem );
	
}
