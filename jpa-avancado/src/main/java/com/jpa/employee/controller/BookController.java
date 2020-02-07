package com.jpa.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.book.model.Book;
import com.jpa.book.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	BookService service;

//	@GetMapping("/update")
//	public Book create() {		
//		service.saveBookAndPages();
//		return null;
//	}
	
	@GetMapping("/delete")
	public Book deleteBookAndPages() {
		service.deleteSpecificaRelationshipBookPage();
		return null;
	}
}
