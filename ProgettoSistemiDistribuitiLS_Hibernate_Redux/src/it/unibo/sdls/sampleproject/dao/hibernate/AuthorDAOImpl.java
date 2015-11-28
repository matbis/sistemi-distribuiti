package it.unibo.sdls.sampleproject.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import it.unibo.sdls.sampleproject.dao.Author;
import it.unibo.sdls.sampleproject.dao.AuthorDAO;

public class AuthorDAOImpl implements AuthorDAO {

	public int insertAuthor(Author author) {
		Session session = openSession();
		Transaction tx = null;
		int id = 0;
		try {
			tx = session.beginTransaction();
			id = (Integer) session.save(author);
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

	public int removeAuthorByName(String name) {
		Session session = openSession();
		Transaction tx = null;
		int count = 0;
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Author.class);
			criteria.add(Restrictions.eq("name", name));
			List<Author> authors = criteria.list();
			count = authors.size();
			for(Author author : authors) {
				session.delete(author);
			}
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
		return count;
	}

	public int removeAuthorById(int id) {
		Session session = openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(session.load(Author.class, id));
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

	public Author findAuthorByName(String name) {
		Session session = openSession();
		Transaction tx = null;
		Author author = null;
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Author.class);
			criteria.add(Restrictions.eq("name", name));
			List<Author> authors = criteria.list();
			author = authors.get(0);
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
		return author;
	}

	public Author findAuthorById(int id) {
		Session session = openSession();
		Transaction tx = null;
		Author author = null;
		try {
			tx = session.beginTransaction();
			author = (Author) session.load(Author.class, id);
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
		return author;
	}

	public List<Author> findAllAuthors() {
		Session session = openSession();
		Transaction tx = null;
		List<Author> authors = null;
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Author.class);
			authors = criteria.list();
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
		return authors;
	}
	
	private Session openSession() {
		return new Configuration().configure().buildSessionFactory().openSession();
	}

}
