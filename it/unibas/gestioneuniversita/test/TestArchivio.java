package it.unibas.gestioneuniversita.test;

import it.unibas.gestioneuniversita.persistenza.*;
import it.unibas.gestioneuniversita.modello.*;
import junit.framework.*;
import org.slf4j.*;
import java.util.*;

public class TestArchivio extends TestCase {
	private Archivio archivio;
	private Logger logger = LoggerFactory.getLogger(TestArchivio.class);

	public void setUp() {
		this.archivio = DAOArchivioMock.carica();
	}

	public void testVerificaSeSonoGliStessi() {
		Archivio archivio2 = new Archivio();
		Universita uni1 = new Universita("Universita degli studi della basilicata", "Potenza");
		uni1.addStudente(new Studente("666666", "Rocco", "Cortese", 20, 70, "Vaglio", "Basilicata"));
		uni1.addStudente(new Studente("222222", "Rocco", "Trolio", 21, 60, "Oppido", "Basilicata"));
		uni1.addStudente(new Studente("444444", "Nicola", "Santorsa", 10, 76, "Potenza", "Basilicata"));
		Universita uni2 = new Universita("Universita degli studi del molise", "Campobasso");
		uni2.addStudente(new Studente("333333", "Alessandro", "Pasqua", 21, 62, "Potenza", "Basilicata"));
		uni2.addStudente(new Studente("111111", "Carmine", "Inserrato", 27, 69, "Campobasso", "Basilicata"));
		archivio2.addUniversita(uni1);
		archivio2.addUniversita(uni2);
		assertFalse(archivio2.verificaSeSonoGliStessi());
	}

	public void testVerificaSeSonoGliStessi2() {
		assertTrue(this.archivio.verificaSeSonoGliStessi());
	}

	public void testInsiemeRegioniConDuplicati() {
		Set<String> insieme = this.archivio.insiemeRegioniConDuplicati();
		assertTrue(insieme.size() == 0);
	}

	public void testInsiemeRegioniConDuplicati2() {
		Archivio archivio2 = new Archivio();
		Universita uni1 = new Universita("Universita degli studi della basilicata", "Potenza");
		uni1.addStudente(new Studente("666666", "Rocco", "Cortese", 0, 0, "Vaglio", "Basilicata"));
		uni1.addStudente(new Studente("666666", "Rocco", "Cortese", 2, 7, "Vaglio", "Basilicata"));
		uni1.addStudente(new Studente("666666", "Rocco", "Cortese", 21, 71, "Vaglio", "Basilicata"));
		uni1.addStudente(new Studente("222222", "Rocco", "Trolio", 21, 6, "Oppido", "molise"));
		uni1.addStudente(new Studente("222222", "Rocco", "Trolio", 2, 60, "Oppido", "molise"));
		uni1.addStudente(new Studente("444444", "Nicola", "Santorsa", 10, 76, "Potenza", "abruzzo"));
		Universita uni2 = new Universita("Universita degli studi del molise", "Campobasso");
		uni2.addStudente(new Studente("333333", "Alessandro", "Pasqua", 21, 62, "Potenza", "abruzzo"));
		uni2.addStudente(new Studente("111111", "Carmine", "Inserrato", 27, 69, "Campobasso", "campania"));
		archivio2.addUniversita(uni1);
		archivio2.addUniversita(uni2);
		Set<String> insieme = archivio2.insiemeRegioniConDuplicati();
		assertTrue(insieme.size() == 2);
		assertTrue(insieme.contains("Basilicata"));
		assertTrue(insieme.contains("molise"));
		assertTrue(!insieme.contains("abruzzo"));
	}

	public void testDueRegioniConPiuOmonimi() {
		Set<String> insieme = this.archivio.dueRegioniConPiuOmonimi();
		logger.debug("INSIEME TO STRING" + insieme.toString());
		assertTrue(insieme.size() == 0);
	}

	public void testUniversitaConPiuFuorisede() {
		Universita uni = this.archivio.universitaConPiuFuorisede();
		logger.debug(uni.toString());
		assertTrue(uni)
	}
}