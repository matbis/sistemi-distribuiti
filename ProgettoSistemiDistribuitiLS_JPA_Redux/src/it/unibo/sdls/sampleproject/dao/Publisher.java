package it.unibo.sdls.sampleproject.dao;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Publisher {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int id;
	protected String name;
	@OneToMany(mappedBy="book", cascade=CascadeType.ALL)
	protected Set<Book> books;
	
	// ------------------------------------------
	// Constructors
	// ------------------------------------------
	public Publisher() {}
	
	public Publisher(String name) {
		this.name = name;
	}
	
	// ------------------------------------------
	// Getter and Setter methods
	// ------------------------------------------

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
}
