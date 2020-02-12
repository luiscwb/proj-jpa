package com.jpa.book.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.book.model.Book;
import com.jpa.book.model.Page;
import com.jpa.book.repo.BookRepository;
import com.jpa.book.repo.PageRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	PageRepository pageRepository;
	

	@Transactional
	public Book saveBookAndPages(Book book, ArrayList<Page> pages) {

		bookRepository.save(book);
		pages.forEach( p -> book.addPage(p)); //Aqui esta salvando usando cascate, cuidado com este metodo
		//pageRepository.save(p); // Aqui savla sem usar cascate, as vezes pode ser mais performatico
					
		return null;
	}
	
	
	@Transactional
	//Apaga especificamente um relaçao entre Book e Page. Usa o cascate para apagar de Page a partir da referencia Book da base.
	public void deleteSpecificaRelationshipBookPage() {
		Book book = bookRepository.findById(3L).get();
		book.getPages().remove(1);
	}
}
