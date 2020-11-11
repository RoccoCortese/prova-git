package it.unibas.gestioneuniversita.persistenza;

import it.unibas.gestioneuniversita.modello.*;

public class DAOArchivioMock{
	public static Archivio carica() {
		Archivio archivio = new Archivio();
		Universita uni1 = new Universita("Universita degli studi della basilicata", "Potenza");
		uni1.addStudente(new Studente("666666", "Rocco", "Cortese", 20, 70, "Vaglio", "Basilicata"));
		uni1.addStudente(new Studente("222222", "Rocco", "Trolio", 21, 60, "Oppido", "Basilicata"));
		uni1.addStudente(new Studente("444444", "Nicola", "Santorsa", 10, 76, "Potenza", "Basilicata"));
		Universita uni2 = new Universita("Universita degli studi del molise", "Campobasso");
		uni2.addStudente(new Studente("555555", "Valerio", "Rago", 20, 72, "Pignola", "Basilicata"));
		uni2.addStudente(new Studente("333333", "Alessandro", "Pasqua", 21, 62, "Potenza", "Basilicata"));
		uni2.addStudente(new Studente("111111", "Carmine", "Inserrato", 27, 69, "Potenza", "Basilicata"));
		archivio.addUniversita(uni1);
		archivio.addUniversita(uni2);
		return archivio;
	}
}