package it.unibo.sdls.sampleproject.dao.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import it.unibo.sdls.sampleproject.dao.AuthorDAO;
import it.unibo.sdls.sampleproject.dao.BookDAO;
import it.unibo.sdls.sampleproject.dao.PublisherDAO;

public class TxDAOFactoryImpl extends TxDAOFactory {

	private static EntityManagerFactory emf = null;
	static {
		emf = Persistence.createEntityManagerFactory("sistemiDistribuitiLS");
	}
	private EntityManager em = null;
	private EntityTransaction tx = null;
	
	public TxDAOFactoryImpl() {
		em = emf.createEntityManager();
		tx = em.getTransaction();
		tx.begin();
	}
	
	@Override
	protected void terminate() {
		if(em != null && em.isOpen()) {
			tx.commit();
			em.close();
		}
	}

	@Override
	public AuthorDAO getAuthorDAO() {
		return new AuthorJPAImpl(this);
	}

	@Override
	public BookDAO getBookDAO() {
		return new BookJPAImpl(this);
	}

	@Override
	public PublisherDAO getPublisherDAO() {
		return new PublisherJPAImpl(this);
	}

	public EntityManager getEntityManager() {
		return this.em;
	}
}
