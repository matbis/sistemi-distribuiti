package it.unibo.sdls.sampleproject.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;

import it.unibo.sdls.sampleproject.dao.Author;
import it.unibo.sdls.sampleproject.dao.Book;
import it.unibo.sdls.sampleproject.dao.BookDAO;
import it.unibo.sdls.sampleproject.dao.Publisher;

public class BookJPAImpl implements BookDAO {

	//EntityManagerFactory emf = null;
	EntityManager em = null;
	//EntityTransaction tx = null;
	
	public BookJPAImpl(TxDAOFactoryImpl factory) {
		//emf = Persistence.createEntityManagerFactory("sistemiDistribuitiLS");
		this.em = factory.getEntityManager();
	}
	
	public int addBook(Book book) {
		//em = emf.createEntityManager();
		try {
			//tx = em.getTransaction();
			//tx.begin();
			if(em.find(Book.class, book.getId()) != null) {
				book = em.merge(book);
			} else {
				em.persist(book);
			}			
			//tx.commit();
		} catch(Exception e) {
//			if(tx != null && tx.isActive())
//				tx.rollback();
		    e.printStackTrace();
		} finally {
			em.close();
		}
		return book.getId();
	}

	public int deleteBook(int id) {
		//em = emf.createEntityManager();
		try {
			//tx = em.getTransaction();
			//tx.begin();
			em.remove(em.find(Book.class, id));
			//tx.commit();
		} catch(Exception e) {
//			if(tx != null && tx.isActive())
//				tx.rollback();
		    e.printStackTrace();
		} finally {
			em.close();
		}
		return id;
	}

	public Book getBookById(int id) {
		//em = emf.createEntityManager();
		Book book = null;
		try {
			//tx = em.getTransaction();
			//tx.begin();
			book = em.find(Book.class, id);
			//tx.commit();
		} catch(Exception e) {
//			if(tx != null && tx.isActive())
//				tx.rollback();
		    e.printStackTrace();
		} finally {
			em.close();
		}
		return book;
	}

	public Book getBookByISBN10(String isbn10) {
		//em = emf.createEntityManager();
		Book book = null;
		try {
			//tx = em.getTransaction();
			//tx.begin();
			List<Book> books = em.createQuery("SELECT b FROM Book b WHERE b.isbn10 LIKE :isbn10").setParameter("isbn10", isbn10).getResultList();
			book = books.get(0);
			//tx.commit();
		} catch(Exception e) {
//			if(tx != null && tx.isActive())
//				tx.rollback();
		    e.printStackTrace();
		} finally {
			em.close();
		}
		return book;
	}

	public Book getBookByISBN13(String isbn13) {
		//em = emf.createEntityManager();
		Book book = null;
		try {
			//tx = em.getTransaction();
			//tx.begin();
			List<Book> books = em.createQuery("SELECT b FROM Book b WHERE b.isbn13 LIKE :isbn13").setParameter("isbn13", isbn13).getResultList();
			book = books.get(0);
			//tx.commit();
		} catch(Exception e) {
//			if(tx != null && tx.isActive())
//				tx.rollback();
		    e.printStackTrace();
		} finally {
			em.close();
		}
		return book;
	}

	public Book getBookByTitle(String title) {
		//em = emf.createEntityManager();
		Book book = null;
		try {
			//tx = em.getTransaction();
			//tx.begin();
			List<Book> books = em.createQuery("SELECT b FROM Book b WHERE b.title LIKE :title").setParameter("title", title).getResultList();
			book = books.get(0);
			//tx.commit();
		} catch(Exception e) {
//			if(tx != null && tx.isActive())
//				tx.rollback();
		    e.printStackTrace();
		} finally {
			em.close();
		}
		return book;
	}

	public List<Book> getAllBooks() {
		//em = emf.createEntityManager();
		List<Book> books = null;
		try {
			//tx = em.getTransaction();
			//tx.begin();
			books = em.createQuery("SELECT b FROM Book b").getResultList();
			//tx.commit();
		} catch(Exception e) {
//			if(tx != null && tx.isActive())
//				tx.rollback();
		    e.printStackTrace();
		} finally {
			em.close();
		}
		return books;
	}

	public List<Book> getAllBooksByAuthor(Author author) {
		//em = emf.createEntityManager();
		List<Book> books = null;
		try {
			//tx = em.getTransaction();
			//tx.begin();
			books = em.createQuery("SELECT b FROM Book b JOIN b.authors a WHERE a.id = :id").setParameter("id", author.getId()).getResultList();
			//tx.commit();
		} catch(Exception e) {
//			if(tx != null && tx.isActive())
//				tx.rollback();
		    e.printStackTrace();
		} finally {
			em.close();
		}
		return books;
	}

	public List<Book> getAllBooksByPublisher(Publisher publisher) {
		//em = emf.createEntityManager();
		List<Book> books = null;
		try {
			//tx = em.getTransaction();
			//tx.begin();
			books = em.createQuery("SELECT b FROM Book b WHERE b.publisher LIKE :publisher").setParameter("publisher", publisher).getResultList();
			//tx.commit();
		} catch(Exception e) {
//			if(tx != null && tx.isActive())
//				tx.rollback();
		    e.printStackTrace();
		} finally {
			em.close();
		}
		return books;
	}

}
