package it.unibo.sdls.sampleproject.dao.ejb3;

import javax.ejb.Remote;

import it.unibo.sdls.sampleproject.dao.BookDAO;

@Remote
public interface BookDAOBeanRemote extends BookDAO {

}
