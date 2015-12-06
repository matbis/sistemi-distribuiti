package it.unibo.sdls.sampleproject.dao.ejb3;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unibo.sdls.sampleproject.dao.Author;

/**
 * Session Bean implementation class AuthorDAOBean
 */
@Stateless
public class AuthorDAOBean implements AuthorDAOBeanRemote, AuthorDAOBeanLocal {

    @PersistenceContext 
    EntityManager em;
    
    public AuthorDAOBean() {
     
    }

	@Override
	public int insertAuthor(Author author) {
		try {
			if(em.find(Author.class, author.getId()) != null) {
				author = em.merge(author);
			} else {
				em.persist(author);
			}
		} catch(Exception e) {
		    e.printStackTrace();
		}
		return author.getId();
	}

	@Override
	public int removeAuthorByName(String name) {
		
		int count = 0;
		try {
			List<Author> authors = em.createQuery("SELECT a FROM Author WHERE a.name LIKE :name").setParameter("name", name).getResultList();
			count = authors.size();
			for(Author a : authors) {
				em.remove(a);
			}
		} catch(Exception e) {
		    e.printStackTrace();
		}
		return count;
	}

	@Override
	public int removeAuthorById(int id) {
		try {
			em.remove(em.find(Author.class, id));
		} catch(Exception e) {
		    e.printStackTrace();
		}
		return id;
	}

	@Override
	public Author findAuthorByName(String name) {
		Author author = null;
		try {
			List<Author> authors = em.createQuery("SELECT a FROM Author a WHERE a.name LIKE :name").setParameter("name", name).getResultList();
			author = authors.get(0);
		} catch(Exception e) {
		    e.printStackTrace();
		}
		return author;
	}

	@Override
	public Author findAuthorById(int id) {
		Author author = null;
		try {
			author = em.find(Author.class, id);
		} catch(Exception e) {
		    e.printStackTrace();
		}
		return author;
	}

	@Override
	public List<Author> findAllAuthors() {
		List<Author> authors = null;
		try {
			authors = em.createQuery("SELECT a FROM Author a").getResultList();
		} catch(Exception e) {
		    e.printStackTrace();
		}
		return authors;
	}

}
