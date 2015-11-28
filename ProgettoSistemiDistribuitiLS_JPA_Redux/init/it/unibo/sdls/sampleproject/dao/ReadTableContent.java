package it.unibo.sdls.sampleproject.dao;

import it.unibo.sdls.sampleproject.dao.hibernate.TxDAOFactory;

import java.util.List;

public class ReadTableContent {

	public static void main(String[] args) {

		Utilities.out("--- Reading tables ---");

		// Scelta dao
		String dao = null;
		if ( args.length > 0 )
			dao = args[0];
		else
			return;
		
		// recupero dei dao
		TxDAOFactory txDAOFactory = TxDAOFactory.get(dao);
		BookDAO bookDAO = txDAOFactory.getBookDAO();
		AuthorDAO authorDAO = txDAOFactory.getAuthorDAO();
		PublisherDAO publisherDAO = txDAOFactory.getPublisherDAO();

		// riletture
		List<Book> books = bookDAO.getAllBooks();
		for ( Book book : books ) {
			Utilities.out( Utilities.print( book ) );
		}
		
		List<Author> authors = authorDAO.findAllAuthors();
		for ( Author author : authors ) {
			Utilities.out( Utilities.print( author ) );
		}

		List<Publisher> publishers = publisherDAO.findAllPublishers();
		for ( Publisher publisher : publishers ) {
			Utilities.out( Utilities.print( publisher ) );
		}

		// this simulates the client request's end
		TxDAOFactory.release(txDAOFactory);
		
	}

}
