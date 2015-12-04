package it.unibo.sdls.sampleproject.dao.test;

public class TxPublisherDAOTestsImpl extends TxPublisherDAOTests {

	@Override
	protected String getDAOFactoryCanonicalClassName() {
		return "it.unibo.sdls.sampleproject.dao.hibernate.TxDAOFactoryImpl";
	}

	

}
