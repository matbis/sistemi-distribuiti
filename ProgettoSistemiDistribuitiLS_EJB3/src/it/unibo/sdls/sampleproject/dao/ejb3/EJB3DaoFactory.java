package it.unibo.sdls.sampleproject.dao.ejb3;

import java.util.Hashtable;

import javax.naming.InitialContext;

//import org.jboss.system.server.ServerInfo;

import it.unibo.sdls.sampleproject.dao.AuthorDAO;
import it.unibo.sdls.sampleproject.dao.BookDAO;
import it.unibo.sdls.sampleproject.dao.DAOFactory;
import it.unibo.sdls.sampleproject.dao.PublisherDAO;

public class EJB3DaoFactory extends DAOFactory {
	
	private static InitialContext getInitialContext() throws Exception {
		Hashtable props = getInitialContextProperties();
		return new InitialContext(props);
	}
	
	@SuppressWarnings("unchecked")
	private static Hashtable getInitialContextProperties() {
		Hashtable props = new Hashtable();
		props.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		props.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
//		props.put("java.naming.provider.url", new ServerInfo().getHostAddress() + ":1099");
		props.put("java.naming.provider.url", "127.0.0.1:1099");
		return props;
	}
	
	@Override
	public AuthorDAO getAuthorDAO() {
		try {
			InitialContext context = getInitialContext();
			AuthorDAO result = (AuthorDAO)context.lookup("Progetto_EJB3/AuthorDAOBean/remote");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BookDAO getBookDAO() {
		try {
			InitialContext context = getInitialContext ();
			BookDAO result = (BookDAO)context.lookup("Progetto_EJB3/BookDAOBean/remote");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PublisherDAO getPublisherDAO() {
		try {
			InitialContext context = getInitialContext();
			PublisherDAO result = (PublisherDAO)context.lookup("Progetto_EJB3/PublisherDAOBean/remote");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
