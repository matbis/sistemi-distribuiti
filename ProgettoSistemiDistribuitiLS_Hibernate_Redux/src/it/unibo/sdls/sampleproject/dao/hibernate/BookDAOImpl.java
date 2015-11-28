package it.unibo.sdls.sampleproject.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import it.unibo.sdls.sampleproject.dao.Author;
import it.unibo.sdls.sampleproject.dao.Book;
import it.unibo.sdls.sampleproject.dao.BookDAO;
import it.unibo.sdls.sampleproject.dao.Publisher;

public class BookDAOImpl implements BookDAO {

	public int addBook(Book book) {
		Session session = openSession();
		Transaction tx = null;
		int id = 0;
		try {
			tx = session.beginTransaction();
			id = (Integer) session.save(book);
			tx.commit();
		} catch (Exception e) {
			if (tx != null){
		    	  try{
		    		  tx.rollback();
		    	  }
		    	  catch(Exception e2){
		    		  e2.printStackTrace();
		    	  }
		      }
		      e.printStackTrace();
		} finally {
			session.close();
		}
		return id;
	}

	public int deleteBook(int id) {
		Session session = openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(session.load(Book.class, id));
			tx.commit();
		} catch (Exception e) {
			if (tx != null){
		    	  try{
		    		  tx.rollback();
		    	  }
		    	  catch(Exception e2){
		    		  e2.printStackTrace();
		    	  }
		      }
		      e.printStackTrace();
		} finally {
			session.close();
		}
		return id;
	}

	public Book getBookById(int id) {
		Session session = openSession();
		Transaction tx = null;
		Book book = null;
		try {
			tx = session.beginTransaction();
			book = (Book) session.load(Book.class, id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null){
		    	  try{
		    		  tx.rollback();
		    	  }
		    	  catch(Exception e2){
		    		  e2.printStackTrace();
		    	  }
		      }
		      e.printStackTrace();
		} finally {
			session.close();
		}
		return book;
	}

	public Book getBookByISBN10(String isbn10) {
		Session session = openSession();
		Transaction tx = null;
		Book book = null;
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Book.class);
			criteria.add(Restrictions.eq("isbn10", isbn10));
			List<Book> books = criteria.list();
			book = books.get(0);
			tx.commit();
		} catch (Exception e) {
			if (tx != null){
		    	  try{
		    		  tx.rollback();
		    	  }
		    	  catch(Exception e2){
		    		  e2.printStackTrace();
		    	  }
		      }
		      e.printStackTrace();
		} finally {
			session.close();
		}
		return book;
	}

	public Book getBookByISBN13(String isbn13) {
		Session session = openSession();
		Transaction tx = null;
		Book book = null;
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Book.class);
			criteria.add(Restrictions.eq("isbn13", isbn13));
			List<Book> books = criteria.list();
			book = books.get(0);
			tx.commit();
		} catch (Exception e) {
			if (tx != null){
		    	  try{
		    		  tx.rollback();
		    	  }
		    	  catch(Exception e2){
		    		  e2.printStackTrace();
		    	  }
		      }
		      e.printStackTrace();
		} finally {
			session.close();
		}
		return book;
	}

	public Book getBookByTitle(String title) {
		Session session = openSession();
		Transaction tx = null;
		Book book = null;
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Book.class);
			criteria.add(Restrictions.eq("title", title));
			List<Book> books = criteria.list();
			book = books.get(0);
			tx.commit();
		} catch (Exception e) {
			if (tx != null){
		    	  try{
		    		  tx.rollback();
		    	  }
		    	  catch(Exception e2){
		    		  e2.printStackTrace();
		    	  }
		      }
		      e.printStackTrace();
		} finally {
			session.close();
		}
		return book;
	}

	public List<Book> getAllBooks() {
		Session session = openSession();
		Transaction tx = null;
		List<Book> books = null;
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Book.class);
			books = criteria.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null){
		    	  try{
		    		  tx.rollback();
		    	  }
		    	  catch(Exception e2){
		    		  e2.printStackTrace();
		    	  }
		      }
		      e.printStackTrace();
		} finally {
			session.close();
		}
		return books;
	}

	public List<Book> getAllBooksByAuthor(Author author) {
		Session session = openSession();
		Transaction tx = null;
		List<Book> books = null;
		try {
			tx = session.beginTransaction();
			String query = "from Book b join b.authors a where a.id = :id";
			books = session.createQuery(query).setInteger("id", author.getId()).list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null){
		    	  try{
		    		  tx.rollback();
		    	  }
		    	  catch(Exception e2){
		    		  e2.printStackTrace();
		    	  }
		      }
		      e.printStackTrace();
		} finally {
			session.close();
		}
		return books;
	}

	public List<Book> getAllBooksByPublisher(Publisher publisher) {
		Session session = openSession();
		Transaction tx = null;
		List<Book> books = null;
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Book.class);
			criteria.add(Restrictions.eq("publisher", publisher));
			books = criteria.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null){
		    	  try{
		    		  tx.rollback();
		    	  }
		    	  catch(Exception e2){
		    		  e2.printStackTrace();
		    	  }
		      }
		      e.printStackTrace();
		} finally {
			session.close();
		}
		return books;
	}
	
	private Session openSession() {
		return new Configuration().configure().buildSessionFactory().openSession();
	}

}
