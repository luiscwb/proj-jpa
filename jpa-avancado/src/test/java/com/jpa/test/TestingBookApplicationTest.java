package com.jpa.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpa.book.model.Book;
import com.jpa.book.model.Page;
import com.jpa.book.repo.PageRepository;
import com.jpa.book.service.BookService;

@SpringBootTest
@AutoConfigureMockMvc
public class TestingBookApplicationTest {

	
	@Autowired
	private BookService bookService;

	@Autowired
	private PageRepository pageRepository;
	
	@Test
	public void testInsert() throws Exception {
		Book book = Book.createInstance("La casa del pepe", "Pepe Mujica");
		ArrayList<Page> pages = new ArrayList<>();
		// Para criar cada pagena deve ser enviado o book respetivo
		// Page define o relacionamento, eh a parte forte
		pages.add(Page.createInstance("Pagina1", 1, book));
		pages.add(Page.createInstance("Pagina2", 2, book));
		pages.add(Page.createInstance("Pagina3", 2, book));
		
		bookService.saveBookAndPages(book, pages);
		assertEquals(1, 1);
	}

	@Test
	public void testDeleteJPQL() throws Exception {
		pageRepository.deleteByBookId(5L);
		assertEquals(1, 1);
	}
}
