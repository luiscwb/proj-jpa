package com.jpa.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.jpa.book.model.Book;
import com.jpa.book.model.Page;
import com.jpa.book.repo.PageRepository;
import com.jpa.book.service.BookService;

@SpringBootTest
@AutoConfigureMockMvc
public class TestingBookApplicationTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private BookService bookService;

	@Autowired
	private PageRepository pageRepository;
	
	@Test
	public void testInsert() throws Exception {
		Book book = new Book("La casa del pepe", "Pepe Mujica");
		ArrayList<Page> pages = new ArrayList<>();
		pages.add(new Page("Pagina1", 1, book));
		pages.add(new Page("Pagina2", 2, book));
		pages.add(new Page("Pagina3", 2, book));
		
		bookService.saveBookAndPages(book, pages);
		assertEquals(1, 1);
	}

	@Test
	public void testDeleteJPQL() throws Exception {
		pageRepository.deleteByBookId(5L);
		assertEquals(1, 1);
	}
}
