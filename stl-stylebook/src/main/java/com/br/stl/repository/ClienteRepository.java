package com.br.stl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.br.stl.model.entity.Cliente;
import com.br.stl.model.entity.Imagen;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	
	@Query(value = "Select * from cliente c Where c.cpf = :cpf", nativeQuery = true)
	public Cliente findByCpf( @Param("cpf") String cpf );
	
	
	// Usa construtor costumizado de imagen
	@Query(
	value = 	
	" SELECT new Imagen(im.id,im.descricao,im.inclussao)" +
	" FROM 	 Cliente cli JOIN cli.imagens im" +
	" WHERE  cli.id = :id" 		
	)
	public List<Imagen> findImagesByClientId( @Param("id") Long idCliente);

	
	@Modifying
	@Query(value = "Delete from cliente c Where c.cpf = :cpf", nativeQuery = true)
	public Cliente deleteByCpf( @Param("cpf") String cpf );

	
	@Modifying
	@Query(value = "Insert Into cliente_imagens (cliente_id, imagens_id) values (:idCliente, :idImagen)", nativeQuery = true)
	public void addImageForClient( @Param("idCliente") Long idCliente, @Param("idImagen") Long idImagen );
	
	
	@Modifying
	@Query(value = "Delete From cliente_imagens Where cliente_id = :idCliente And imagens_id = :idImagen", nativeQuery = true)
	public void delImageForClient( @Param("idCliente") Long idCliente, @Param("idImagen") Long idImagen );


	
	
	
	
	// Testes e validações
	@Modifying
	@Query(value = "Delete From cliente_imagens", nativeQuery = true)
	public void delAllRelationImage();
	

	
	// Testes e validações
	@Query(value = "Select count(*) From cliente_imagens", nativeQuery = true)
	public long countImages();
	


}
