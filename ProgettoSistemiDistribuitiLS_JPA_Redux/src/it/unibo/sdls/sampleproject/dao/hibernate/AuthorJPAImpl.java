package it.unibo.sdls.sampleproject.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import it.unibo.sdls.sampleproject.dao.Author;
import it.unibo.sdls.sampleproject.dao.AuthorDAO;

public class AuthorJPAImpl implements AuthorDAO {
	
	private static EntityManagerFactory emf = null;
	static {
		emf = Persistence.createEntityManagerFactory("sistemiDistribuitiLS");
	}
	EntityManager em = null;
	EntityTransaction tx = null;
	
	public AuthorJPAImpl() {
		//emf = Persistence.createEntityManagerFactory("sistemiDistribuitiLS");
	}
	
	public int insertAuthor(Author author) {
		em = emf.createEntityManager();
		try {
			tx = em.getTransaction();
			tx.begin();
			if(em.find(Author.class, author.getId()) != null) {
				author = em.merge(author);
			} else {
				em.persist(author);
			}
			tx.commit();
		} catch(Exception e) {
			if(tx != null && tx.isActive())
				tx.rollback();
		    e.printStackTrace();
		} finally {
			em.close();
		}
		return author.getId();
	}

	public int removeAuthorByName(String name) {
		em = emf.createEntityManager();
		int count = 0;
		try {
			tx = em.getTransaction();
			tx.begin();
			List<Author> authors = em.createQuery("SELECT a FROM Author WHERE a.name LIKE :name").setParameter("name", name).getResultList();
			count = authors.size();
			for(Author a : authors) {
				em.remove(a);
			}
			tx.commit();
		} catch(Exception e) {
			if(tx != null && tx.isActive())
				tx.rollback();
		    e.printStackTrace();
		} finally {
			em.close();
		}
		return count;
	}

	public int removeAuthorById(int id) {
		em = emf.createEntityManager();
		try {
			tx = em.getTransaction();
			tx.begin();
			em.remove(em.find(Author.class, id));
			tx.commit();
		} catch(Exception e) {
			if(tx != null && tx.isActive())
				tx.rollback();
		    e.printStackTrace();
		} finally {
			em.close();
		}
		return id;
	}

	public Author findAuthorByName(String name) {
		em = emf.createEntityManager();
		Author author = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			List<Author> authors = em.createQuery("SELECT a FROM Author a WHERE a.name LIKE :name").setParameter("name", name).getResultList();
			author = authors.get(0);
			tx.commit();
		} catch(Exception e) {
			if(tx != null && tx.isActive())
				tx.rollback();
		    e.printStackTrace();
		} finally {
			em.close();
		}
		return author;
	}

	public Author findAuthorById(int id) {
		em = emf.createEntityManager();
		Author author = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			author = em.find(Author.class, id);
			tx.commit();
		} catch(Exception e) {
			if(tx != null && tx.isActive())
				tx.rollback();
		    e.printStackTrace();
		} finally {
			em.close();
		}
		return author;
	}

	public List<Author> findAllAuthors() {
		em = emf.createEntityManager();
		List<Author> authors = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			authors = em.createQuery("SELECT a FROM Author a").getResultList();
			tx.commit();
		} catch(Exception e) {
			if(tx != null && tx.isActive())
				tx.rollback();
		    e.printStackTrace();
		} finally {
			em.close();
		}
		return authors;
	}

}
