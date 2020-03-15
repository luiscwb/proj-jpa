package com.br.stl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.br.stl.model.entity.Cliente;
import com.br.stl.model.entity.Consultora;
import com.br.stl.model.entity.Dossie;

public interface ConsultoraRepository extends CrudRepository<Consultora, Long> {

	@Query(
	value = 	
	" SELECT do" +
	" FROM 	 Consultora co JOIN co.clientes cl JOIN cl.dossies do" +
	" WHERE  co.id = :id" 		
	)
	public List<Dossie> findDossiesByConsultoraId( @Param("id") Long idConsultora);

	@Query(
	value = 	
	" SELECT cl" +
	" FROM 	 Consultora co JOIN co.clientes cl" +
	" WHERE  co.id = :id" 		
	)
	public List<Cliente> findClientesByConsultoraId( @Param("id") Long idConsultora);
	
	
}
