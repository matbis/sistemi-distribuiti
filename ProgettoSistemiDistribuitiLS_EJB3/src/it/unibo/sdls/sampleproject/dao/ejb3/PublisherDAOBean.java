package it.unibo.sdls.sampleproject.dao.ejb3;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unibo.sdls.sampleproject.dao.Publisher;

/**
 * Session Bean implementation class PublisherDAOBean
 */
@Stateless
public class PublisherDAOBean implements PublisherDAOBeanRemote, PublisherDAOBeanLocal {

	@PersistenceContext
	EntityManager em;

	public PublisherDAOBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int insertPublisher(Publisher publisher) {
		try {
			if (em.find(Publisher.class, publisher.getId()) != null) {
				publisher = em.merge(publisher);
			} else {
				em.persist(publisher);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return publisher.getId();
	}

	@Override
	public Publisher findPublisherByName(String name) {
		Publisher publisher = null;
		try {
			List<Publisher> publishers = em.createQuery("SELECT p FROM Publisher p WHERE p.name LIKE :name")
					.setParameter("name", name).getResultList();
			publisher = publishers.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return publisher;
	}

	@Override
	public Publisher findPublisherById(int id) {
		Publisher publisher = null;
		try {
			publisher = em.find(Publisher.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return publisher;
	}

	@Override
	public List<Publisher> findAllPublishers() {
		List<Publisher> publishers = null;
		try {
			publishers = em.createQuery("SELECT p FROM Publisher p").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return publishers;
	}

	@Override
	public int removePublisherByName(String name) {
		int count = 0;
		try {
			List<Publisher> publishers = em.createQuery("SELECT p FROM Publisher p WHERE p.name LIKE :name")
					.setParameter("name", name).getResultList();
			count = publishers.size();
			for (Publisher p : publishers) {
				em.remove(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int removePublisherById(int id) {
		try {
			em.remove(em.find(Publisher.class, id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

}
