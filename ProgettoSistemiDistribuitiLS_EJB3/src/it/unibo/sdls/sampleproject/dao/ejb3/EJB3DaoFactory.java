package it.unibo.sdls.sampleproject.dao.ejb3;

import it.unibo.sdls.sampleproject.dao.AuthorDAO;
import it.unibo.sdls.sampleproject.dao.BookDAO;
import it.unibo.sdls.sampleproject.dao.DAOFactory;
import it.unibo.sdls.sampleproject.dao.PublisherDAO;

public class EJB3DaoFactory extends DAOFactory {

	@Override
	public AuthorDAO getAuthorDAO() {
		return new AuthorDAOBean();
	}

	@Override
	public BookDAO getBookDAO() {
		return new BookDAOBean();
	}

	@Override
	public PublisherDAO getPublisherDAO() {
		return new PublisherDAOBean();
	}

}
