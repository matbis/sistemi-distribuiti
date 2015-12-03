package it.unibo.sdls.sampleproject.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;

import it.unibo.sdls.sampleproject.dao.Publisher;
import it.unibo.sdls.sampleproject.dao.PublisherDAO;

public class PublisherJPAImpl implements PublisherDAO {

	//EntityManagerFactory emf = null;
	EntityManager em = null;
	//EntityTransaction tx = null;
	
	public PublisherJPAImpl(TxDAOFactoryImpl factory) {
		//emf = Persistence.createEntityManagerFactory("sistemiDistribuitiLS");
		this.em = factory.getEntityManager();
	}
	
	public int insertPublisher(Publisher publisher) {
		//em = emf.createEntityManager();
		try {
			//tx = em.getTransaction();
			//tx.begin();
			if(em.find(Publisher.class, publisher.getId()) != null) {
				publisher = em.merge(publisher);
			} else {
				em.persist(publisher);
			}
			//tx.commit();
		} catch(Exception e) {
			//if(tx != null && tx.isActive())
			//	tx.rollback();
		    e.printStackTrace();
		} finally {
			em.close();
		}
		return publisher.getId();
	}

	public Publisher findPublisherByName(String name) {
		//em = emf.createEntityManager();
		Publisher publisher = null;
		try {
			//tx = em.getTransaction();
			//tx.begin();
			List<Publisher> publishers = em.createQuery("SELECT p FROM Publisher p WHERE p.name LIKE :name").setParameter("name", name).getResultList();
			publisher = publishers.get(0);
			//tx.commit();
		} catch(Exception e) {
			//if(tx != null && tx.isActive())
			//	tx.rollback();
		    e.printStackTrace();
		} finally {
			em.close();
		}
		return publisher;
	}

	public Publisher findPublisherById(int id) {
		//em = emf.createEntityManager();
		Publisher publisher = null;
		try {
			//tx = em.getTransaction();
			//tx.begin();
			publisher = em.find(Publisher.class, id);
			//tx.commit();
		} catch(Exception e) {
			//if(tx != null && tx.isActive())
			//	tx.rollback();
		    e.printStackTrace();
		} finally {
			em.close();
		}
		return publisher;
	}

	public int removePublisherByName(String name) {
		//em = emf.createEntityManager();
		int count = 0;
		try {
			//tx = em.getTransaction();
			//tx.begin();
			List<Publisher> publishers = em.createQuery("SELECT p FROM Publisher p WHERE p.name LIKE :name").setParameter("name", name).getResultList();
			count = publishers.size();
			for(Publisher p : publishers) {
				em.remove(p);
			}
			//tx.commit();
		} catch(Exception e) {
			//if(tx != null && tx.isActive())
			//	tx.rollback();
		    e.printStackTrace();
		} finally {
			em.close();
		}
		return count;
	}

	public int removePublisherById(int id) {
		//em = emf.createEntityManager();
		try {
			//tx = em.getTransaction();
			//tx.begin();
			em.remove(em.find(Publisher.class, id));
			//tx.commit();
		} catch(Exception e) {
			//if(tx != null && tx.isActive())
			//	tx.rollback();
		    e.printStackTrace();
		} finally {
			em.close();
		}
		return id;
	}

	public List<Publisher> findAllPublishers() {
		//em = emf.createEntityManager();
		List<Publisher> publishers = null;
		try {
			//tx = em.getTransaction();
			//tx.begin();
			publishers = em.createQuery("SELECT p FROM Publisher p").getResultList();
			//tx.commit();
		} catch(Exception e) {
			//if(tx != null && tx.isActive())
			//	tx.rollback();
		    e.printStackTrace();
		} finally {
			em.close();
		}
		return publishers;
	}

}
