package it.unibo.sdls.sampleproject.dao;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="authors")
public class Author implements Serializable {
	
	/**
	 * Serial Version UID 
	 */
	private static final long serialVersionUID = 3103905839384102117L;

	@Id
	@Column(name="author_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int id;
	
	protected String name;
	
	@ManyToMany(mappedBy="authors")
	protected Set<Book> books;
	
	public Author() {}
	
	public Author(String name) {
		this.name = name;
	}
	
	public Author(String name, Set<Book> books) {
		this.name = name;
		this.books = books;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

}
