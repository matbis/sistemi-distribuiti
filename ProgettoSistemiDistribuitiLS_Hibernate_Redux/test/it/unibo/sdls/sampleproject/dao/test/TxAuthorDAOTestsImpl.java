package it.unibo.sdls.sampleproject.dao.test;

public class TxAuthorDAOTestsImpl extends TxAuthorDAOTests {

	@Override
	protected String getDAOFactoryCanonicalClassName() {
		return "it.unibo.sdls.sampleproject.dao.hibernate.TxDAOFactoryImpl";
	}


}
