package com.jpa.book.repo;

import org.springframework.data.repository.CrudRepository;

import com.jpa.book.model.Book;


public interface BookRepository  extends CrudRepository<Book, Long> {

}
