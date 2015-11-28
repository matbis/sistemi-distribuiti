package it.unibo.sdls.sampleproject.dao;

import it.unibo.sdls.sampleproject.dao.hibernate.TxDAOFactory;

import java.util.List;

public class CleanTableContent {

	public static void main(String[] args) {

		Utilities.out("--- Cleaning tables ---");

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
		
		// cancellazioni
		List<Book> books = bookDAO.getAllBooks();
		for ( Book book : books ) {
			bookDAO.deleteBook(book.getId());
		}
		
		List<Author> authors = authorDAO.findAllAuthors();
		for ( Author author : authors ) {
			authorDAO.removeAuthorById(author.getId());
		}

		List<Publisher> publishers = publisherDAO.findAllPublishers();
		for ( Publisher publisher : publishers ) {
			publisherDAO.removePublisherById(publisher.getId());
		}

		// this simulates the client request's end
		TxDAOFactory.release(txDAOFactory);

		ReadTableContent.main(new String[]{dao});

	}

}
