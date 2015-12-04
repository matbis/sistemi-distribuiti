package it.unibo.sdls.sampleproject.dao.ejb3;

import javax.ejb.Remote;

import it.unibo.sdls.sampleproject.dao.PublisherDAO;

@Remote
public interface PublisherDAOBeanRemote extends PublisherDAO {

}
