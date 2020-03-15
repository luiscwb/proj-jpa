package com.br.stl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.br.stl.model.entity.Dossie;
import com.br.stl.model.entity.Imagem;

public interface DossieRepository extends CrudRepository<Dossie, Long> {
	

	// Usa construtor costumizado de imagen
	@Query(
	value = 	
	" SELECT new Imagem(im.id,im.descricao,im.inclussao)" +
	" FROM 	 Dossie do JOIN do.imagens im" +
	" WHERE  do.id = :id" 		
	)
	public List<Imagem> findImagesByDossieId( @Param("id") Long idDossie);
	
	@Modifying
	@Query(value = "Insert Into dossie_imagens (dossie_id, imagens_id) values (:idDossie, :idImagem)", nativeQuery = true)
	public void addImageForDossie( @Param("idDossie") Long idDossie, @Param("idImagem") Long idImagem );
	
	
	@Modifying
	@Query(value = "Delete From dossie_imagens Where dossie_id = :idDossie And imagens_id = :idImagem", nativeQuery = true)
	public void delImageForDossie( @Param("idDossie") Long idDossie, @Param("idImagem") Long idImagem );

}
