package com.jpa.book.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;


@Entity
public class Page{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String title;

    private Integer number;
    
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)	
    private Book book;

    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

    public Page(String title, Integer number, Book book) {
		this.title = title;
		this.number = number;
		this.book = book;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Page)) return false;
        Page book = (Page) o;
        return Objects.equals(title, book.title);
    }

    public Page() {
		super();
	}

	@Override
    public int hashCode() {
        return Objects.hash(title);
    }
}