package com.loclab.persist;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.loclab.entity.Locacao;

public interface LocacaoRepository extends CrudRepository<Locacao, Long> {

	  @Query(value = 
		  "SELECT * "
		  + "FROM locacao l "
		  + "WHERE l.local_id = :idLoc "
		  + "AND :dataInicial <= l.data_final AND :dataFinal >= l.data_inicial ",
	  nativeQuery = true)
	  public List<Locacao> checkAvailability(
			  @Param("idLoc") 		Long 	idLoc,
			  @Param("dataInicial") Date	dataInicial,
			  @Param("dataFinal") 	Date	dataFinal
			  );

	  
	  @Query(value = 
			  "SELECT * "
			  + "FROM locacao l "
			  + "WHERE l.local_id = :idLoc "
			  + "AND (l.data_inicial BETWEEN :dataInicial AND :dataFinal "
			  + "OR  l.data_final   BETWEEN :dataInicial AND :dataFinal)",
		nativeQuery = true)
	  public List<Locacao> findLocationsBetween(
		  @Param("idLoc") 		Long 	idLoc,
		  @Param("dataInicial") Date	dataInicial,
		  @Param("dataFinal") 	Date	dataFinal
	  );
	  
	  
	  @Query(value = "DELETE FROM locacao l WHERE l.data_final < :dataLimite", nativeQuery = true)
	  public void deleteLessThan(@Param("dataLimite") Date date) ;
}
