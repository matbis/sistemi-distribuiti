package it.unibo.sdls.sampleproject.dao.ejb3;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unibo.sdls.sampleproject.dao.Publisher;
import it.unibo.sdls.sampleproject.dao.PublisherDAO;

/**
 * Session Bean implementation class PublisherDAOBean
 */
@Stateless
@Local(PublisherDAO.class)
@Remote(PublisherDAO.class)
public class PublisherDAOBean implements PublisherDAO {

	@PersistenceContext(unitName="SampleProjectUnit")
	EntityManager em;

	public PublisherDAOBean() {
		
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
