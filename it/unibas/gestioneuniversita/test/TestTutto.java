package it.unibas.gestioneuniversita.test;

import junit.framework.*;

public class TestTutto extends TestCase {

	public static TestSuite suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(TestArchivio.class);
		return suite;
	}
}