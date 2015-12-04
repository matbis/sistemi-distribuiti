package it.unibo.sdls.sampleproject.dao.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import it.unibo.sdls.sampleproject.dao.AuthorDAO;
import it.unibo.sdls.sampleproject.dao.BookDAO;
import it.unibo.sdls.sampleproject.dao.PublisherDAO;

public class TxDAOFactoryImpl extends TxDAOFactory {

	private static SessionFactory sf = null;
	static {
		sf = new Configuration().configure().buildSessionFactory();
	}
	private Session s = null;
	private Transaction tx = null;
	private boolean txError = false;
	
	public TxDAOFactoryImpl() {
		s = sf.openSession();
		tx = s.beginTransaction();
	}
	
	@Override
	protected void terminate() {
		if(s != null && s.isOpen()) {
			if(txError)
				tx.rollback();
			else
				tx.commit();
			s.close();
		}
	}

	@Override
	public AuthorDAO getAuthorDAO() {
		return new AuthorDAOImpl(this);
	}

	@Override
	public BookDAO getBookDAO() {
		return new BookDAOImpl(this);
	}

	@Override
	public PublisherDAO getPublisherDAO() {
		return new PublisherDAOImpl(this);
	}

	public Session getSession() {
		return s;
	}

	public void setTxError(boolean txError) {
		this.txError = txError;
	}

}
