package it.unibo.sdls.sampleproject.dao.ejb3;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unibo.sdls.sampleproject.dao.Author;
import it.unibo.sdls.sampleproject.dao.AuthorDAO;

/**
 * Session Bean implementation class AuthorDAOBean
 */
@Stateless
@Local(AuthorDAO.class)
@Remote(AuthorDAO.class)
public class AuthorDAOBean implements AuthorDAO {

    @PersistenceContext(unitName="SampleProjectUnit") 
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
			author = (Author) em.createQuery("SELECT a FROM Author a WHERE a.name LIKE :name").setParameter("name", name).getSingleResult();
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
