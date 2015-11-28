package it.unibo.sdls.sampleproject.dao.hibernate;

import it.unibo.sdls.sampleproject.dao.DAOFactory;

public abstract class TxDAOFactory 
extends DAOFactory {

	public static TxDAOFactory get(String whichFactory) {
		try {
			return (TxDAOFactory) TxDAOFactory.getDAOFactory(whichFactory);
		}
		catch (Throwable t) {
			return null;
		}
	}

	public static void release(TxDAOFactory factory) {
		try {
			factory.terminate();
		}
		catch (Throwable t) { }
	}
	
	abstract protected void terminate();

}
