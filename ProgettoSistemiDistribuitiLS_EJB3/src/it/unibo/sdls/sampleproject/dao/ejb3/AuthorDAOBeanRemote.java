package it.unibo.sdls.sampleproject.dao.ejb3;

import javax.ejb.Remote;

import it.unibo.sdls.sampleproject.dao.AuthorDAO;

@Remote
public interface AuthorDAOBeanRemote extends AuthorDAO{

}
