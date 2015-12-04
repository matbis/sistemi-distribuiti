package it.unibo.sdls.sampleproject.dao.ejb3;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unibo.sdls.sampleproject.dao.Author;
import it.unibo.sdls.sampleproject.dao.Book;
import it.unibo.sdls.sampleproject.dao.Publisher;

/**
 * Session Bean implementation class BookDAOBean
 */
@Stateless
public class BookDAOBean implements BookDAOBeanRemote, BookDAOBeanLocal {

	@PersistenceContext
	EntityManager em;

	public BookDAOBean() {

	}

	@Override
	public int addBook(Book book) {
		try {
			if (em.find(Book.class, book.getId()) != null) {
				book = em.merge(book);
			} else {
				em.persist(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book.getId();
	}

	@Override
	public int deleteBook(int id) {
		try {
			em.remove(em.find(Book.class, id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public Book getBookById(int id) {
		Book book = null;
		try {
			book = em.find(Book.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	@Override
	public Book getBookByISBN10(String isbn10) {
		Book book = null;
		try {
			List<Book> books = em.createQuery("SELECT b FROM Book b WHERE b.isbn10 LIKE :isbn10")
					.setParameter("isbn10", isbn10).getResultList();
			book = books.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	@Override
	public Book getBookByISBN13(String isbn13) {
		Book book = null;
		try {
			List<Book> books = em.createQuery("SELECT b FROM Book b WHERE b.isbn13 LIKE :isbn13")
					.setParameter("isbn13", isbn13).getResultList();
			book = books.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	@Override
	public Book getBookByTitle(String title) {
		Book book = null;
		try {
			List<Book> books = em.createQuery("SELECT b FROM Book b WHERE b.title LIKE :title")
					.setParameter("title", title).getResultList();
			book = books.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> books = null;
		try {
			books = em.createQuery("SELECT b FROM Book b").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public List<Book> getAllBooksByAuthor(Author author) {
		List<Book> books = null;
		try {
			books = em.createQuery("SELECT b FROM Book b JOIN b.authors a WHERE a.id = :id")
					.setParameter("id", author.getId()).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public List<Book> getAllBooksByPublisher(Publisher publisher) {
		List<Book> books = null;
		try {
			books = em.createQuery("SELECT b FROM Book b WHERE b.publisher LIKE :publisher")
					.setParameter("publisher", publisher).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}

}
