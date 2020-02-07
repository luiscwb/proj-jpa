package com.jpa.book.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jpa.book.model.Page;

public interface PageRepository extends CrudRepository<Page, Long> {

	//@Query(value = "SELECT * From Page p Where p.book_id = 50", nativeQuery = true)
	@Query(value = "DELETE From page Where book_id=50", nativeQuery = true)
	public void deleteByBookId(Long id);

}
