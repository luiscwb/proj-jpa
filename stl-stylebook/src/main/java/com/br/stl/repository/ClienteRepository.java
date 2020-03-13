package com.br.stl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.br.stl.model.entity.Cliente;
import com.br.stl.model.entity.Imagem;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	
	@Query(value = "Select * from cliente c Where c.cpf = :cpf", nativeQuery = true)
	public Cliente findByCpf( @Param("cpf") String cpf );
	
	
	// Usa construtor costumizado de imagem
	@Query(
	value = 	
	" SELECT new Imagem(im.id,im.descricao,im.inclussao)" +
	" FROM 	 Cliente cli JOIN cli.imagens im" +
	" WHERE  cli.id = :id" 		
	)
	public List<Imagem> findAllImagesByClientId( @Param("id") Long idCliente);

	
	@Modifying
	@Query(value = "Delete from cliente c Where c.cpf = :cpf", nativeQuery = true)
	public Cliente deleteByCpf( @Param("cpf") String cpf );

	
	@Modifying
	@Query(value = "Insert Into cliente_imagens (cliente_id, imagens_id) values (:idCliente, :idImagem)", nativeQuery = true)
	public void addRelationImage( @Param("idCliente") Long idCliente, @Param("idImagem") Long idImagem );
	
	
	@Modifying
	@Query(value = "Delete From cliente_imagens Where cliente_id = :idCliente And imagens_id = :idImagem", nativeQuery = true)
	public void delRelationImage( @Param("idCliente") Long idCliente, @Param("idImagem") Long idImagen );


	
	
	
	
	// Testes e validações
	@Modifying
	@Query(value = "Delete From cliente_imagens", nativeQuery = true)
	public void delAllRelationImage();
	

	
	// Testes e validações
	@Query(value = "Select count(*) From cliente_imagens", nativeQuery = true)
	public long countImages();
	


}
