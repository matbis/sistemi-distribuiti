package it.unibo.sdls.sampleproject.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import it.unibo.sdls.sampleproject.dao.Publisher;
import it.unibo.sdls.sampleproject.dao.PublisherDAO;

public class PublisherDAOImpl implements PublisherDAO {

	private Session session = null;
	private TxDAOFactoryImpl factory;
	
	public PublisherDAOImpl(TxDAOFactoryImpl factory) {
		session = factory.getSession();
		this.factory = factory;
	}
	
	public int insertPublisher(Publisher publisher) {
		//Session session = openSession();
		//Transaction tx = null;
		int id = 0;
		try {
			//tx = session.beginTransaction();
			id = (Integer) session.save(publisher);
			//tx.commit();
		} catch (Exception e) {
//			if (tx != null){
//		    	  try{
//		    		  tx.rollback();
//		    	  }
//		    	  catch(Exception e2){
//		    		  e2.printStackTrace();
//		    	  }
//		      }
		      e.printStackTrace();
		      factory.setTxError(true);
		} finally {
			//session.close();
		}
		return id;
	}

	public Publisher findPublisherByName(String name) {
		//Session session = openSession();
		//Transaction tx = null;
		Publisher publisher = null;
		try {
			//tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Publisher.class);
			criteria.add(Restrictions.eq("name", name));
			List<Publisher> publishers = criteria.list();
			publisher = publishers.get(0);
			//tx.commit();
		} catch (Exception e) {
//			if (tx != null){
//		    	  try{
//		    		  tx.rollback();
//		    	  }
//		    	  catch(Exception e2){
//		    		  e2.printStackTrace();
//		    	  }
//		      }
		      e.printStackTrace();
		      factory.setTxError(true);
		} finally {
			//session.close();
		}
		return publisher;
	}

	public Publisher findPublisherById(int id) {
		//Session session = openSession();
		//Transaction tx = null;
		Publisher publisher = null;
		try {
			//tx = session.beginTransaction();
			publisher = (Publisher) session.load(Publisher.class, id);
			//tx.commit();
		} catch (Exception e) {
//			if (tx != null){
//		    	  try{
//		    		  tx.rollback();
//		    	  }
//		    	  catch(Exception e2){
//		    		  e2.printStackTrace();
//		    	  }
//		      }
		      e.printStackTrace();
		      factory.setTxError(true);
		} finally {
			//session.close();
		}
		return publisher;
	}

	public List<Publisher> findAllPublishers() {
		//Session session = openSession();
		//Transaction tx = null;
		List<Publisher> publishers = null;
		try {
			//tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Publisher.class);
			publishers = criteria.list();
			//tx.commit();
		} catch (Exception e) {
//			if (tx != null){
//		    	  try{
//		    		  tx.rollback();
//		    	  }
//		    	  catch(Exception e2){
//		    		  e2.printStackTrace();
//		    	  }
//		      }
		      e.printStackTrace();
		      factory.setTxError(true);
		} finally {
			//session.close();
		}
		return publishers;
	}

	public int removePublisherByName(String name) {
		//Session session = openSession();
		//Transaction tx = null;
		int count = 0;
		try {
			//tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Publisher.class);
			criteria.add(Restrictions.eq("name", name));
			List<Publisher> publishers = criteria.list();
			count = publishers.size();
			for(Publisher publisher : publishers) {
				session.delete(publisher);
			}
			//tx.commit();
		} catch (Exception e) {
//			if (tx != null){
//		    	  try{
//		    		  tx.rollback();
//		    	  }
//		    	  catch(Exception e2){
//		    		  e2.printStackTrace();
//		    	  }
//		      }
		      e.printStackTrace();
		      factory.setTxError(true);
		} finally {
			//session.close();
		}
		return count;
	}

	public int removePublisherById(int id) {
		//Session session = openSession();
		//Transaction tx = null;
		try {
			//tx = session.beginTransaction();
			session.delete(session.load(Publisher.class, id));
			//tx.commit();
		} catch (Exception e) {
//			if (tx != null){
//		    	  try{
//		    		  tx.rollback();
//		    	  }
//		    	  catch(Exception e2){
//		    		  e2.printStackTrace();
//		    	  }
//		      }
		      e.printStackTrace();
		      factory.setTxError(true);
		} finally {
			//session.close();
		}
		return id;
	}

//	private Session openSession() {
//		return new Configuration().configure().buildSessionFactory().openSession();
//	}

}
