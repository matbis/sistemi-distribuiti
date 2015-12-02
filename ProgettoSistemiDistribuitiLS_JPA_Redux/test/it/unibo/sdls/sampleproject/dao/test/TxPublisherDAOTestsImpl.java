package it.unibo.sdls.sampleproject.dao.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class TxPublisherDAOTestsImpl extends TxPublisherDAOTests {

	@Override
	protected String getDAOFactoryCanonicalClassName() {
		return "it.unibo.sdls.sampleproject.dao.hibernate.TxDAOFactoryImpl";
	}

}
