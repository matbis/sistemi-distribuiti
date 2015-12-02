package it.unibo.sdls.sampleproject.dao.test;

public class TxBookDAOTestsImpl extends TxBookDAOTests {

	@Override
	protected String getDAOFactoryCanonicalClassName() {
		return "it.unibo.sdls.sampleproject.dao.hibernate.TxDAOFactoryImpl";
	}

}
