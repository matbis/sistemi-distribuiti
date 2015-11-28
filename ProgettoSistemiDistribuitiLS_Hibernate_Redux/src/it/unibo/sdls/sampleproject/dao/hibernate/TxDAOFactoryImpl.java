package it.unibo.sdls.sampleproject.dao.hibernate;

import it.unibo.sdls.sampleproject.dao.AuthorDAO;
import it.unibo.sdls.sampleproject.dao.BookDAO;
import it.unibo.sdls.sampleproject.dao.PublisherDAO;

public class TxDAOFactoryImpl extends TxDAOFactory {

	@Override
	protected void terminate() {
		// TODO Auto-generated method stub

	}

	@Override
	public AuthorDAO getAuthorDAO() {
		return new AuthorDAOImpl();
	}

	@Override
	public BookDAO getBookDAO() {
		return new BookDAOImpl();
	}

	@Override
	public PublisherDAO getPublisherDAO() {
		return new PublisherDAOImpl();
	}

}
