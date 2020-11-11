package it.unibas.gestioneuniversita.modello;

import java.util.*;
import org.slf4j.*;

public class Archivio {
	private List<Universita> listaUniversita = new ArrayList<Universita>();
	private Logger logger = LoggerFactory.getLogger(Archivio.class);

	public List<Universita> getListaUniversita(){
		return this.listaUniversita;
	}

	public void addUniversita (Universita universita) {
		this.listaUniversita.add(universita);
	}

	private List<Studente> listaTutti() {
		List<Studente> listaTutti = new ArrayList<Studente>();
		for(Universita universita : this.listaUniversita) {
			listaTutti.addAll(universita.getListaStudenti());
		}
		return listaTutti;
	}

	private List<Studente> listaTreBravi() {
		List<Studente> lista = this.listaTutti();
		if(lista.size() < 3) {
			throw new IllegalArgumentException("Errore, la lista deve contenere almeno 3 elementi");
		}
		if(lista.size() == 3) {
			return lista;
		}
		List<Studente> listaTreBravi = new ArrayList<Studente>();
		Collections.sort(lista, new ComparatorePerVoto());
		for(int i = 0; i < 3; i++) {
			listaTreBravi.add(lista.get(i));
		}
		return listaTreBravi;
	}

	private List<Studente> listaTreGiovani() {
		List<Studente> lista = this.listaTutti();
		if(lista.size() < 3) {
			throw new IllegalArgumentException("Errore, la lista deve contenere almeno 3 elementi");
		}
		if(lista.size() == 3) {
			return lista;
		}
		List<Studente> listaTreGiovani = new ArrayList<Studente>();
		Collections.sort(lista);
		for(int i = 0; i < 3; i++) {
			listaTreGiovani.add(lista.get(i));
		}
		return listaTreGiovani;
	}

	public boolean verificaSeSonoGliStessi() {
		List<Studente> lista = this.listaTreGiovani();
		logger.debug("LISTA GIOVANI: " + lista.toString());
		List<Studente> lista2 = this.listaTreBravi();
		logger.debug("LISTA Bravi: " + lista2.toString());
		return lista.containsAll(lista2);
	}

	private Map<String, List<String>> mappaRegioniNome() {
		Map<String, List<String>> mappa = new HashMap<String, List<String>>();
		List<Studente> lista = this.listaTutti();
		List<String> lista2;
		for(Studente studente : lista) {
			lista2 = mappa.get(studente.getRegione());
			if(lista2 == null) {
				lista2 = new ArrayList<String>();
				mappa.put(studente.getRegione(), lista2);
			}
			lista2.add(studente.codiceNomeCognome());
		}
		return mappa;
	}

	public Set<String> insiemeRegioniConDuplicati() {
		Map<String, List<String>> mappa = this.mappaRegioniNome();
		Set<String> insieme = new HashSet<String>();
		for(String regione : mappa.keySet()) {
			List<String> listaConDuplicati = mappa.get(regione);
			Set<String> insiemeSenzaDuplicati = new HashSet<String>(listaConDuplicati);
			if(listaConDuplicati.size() != insiemeSenzaDuplicati.size()) {
				insieme.add(regione);
			}
		}
		return insieme;
	}

	private Map<String, List<String>> mappaRegioniNomeCognome() {
		Map<String, List<String>> mappa = new HashMap<String, List<String>>();
		List<Studente> lista = this.listaTutti();
		List<String> lista2;
		for(Studente studente : lista) {
			lista2 = mappa.get(studente.getRegione());
			if(lista2 == null) {
				lista2 = new ArrayList<String>();
				mappa.put(studente.getRegione(), lista2);
			}
			lista2.add(studente.nomeCognome());
		}
		return mappa;
	}

	private Map<String, Integer> mappaRegioniNumeroDuplicati() {
		Map<String, List<String>> mappa = this.mappaRegioniNomeCognome();
		Map<String, Integer> mappa2 = new HashMap<String, Integer>();
		Integer numeroDuplicati;
		for(String regione : mappa.keySet()) {
			List<String> listaConDuplicati = mappa.get(regione);
			Set<String> insiemeSenzaDuplicati = new HashSet<String>(listaConDuplicati);
			numeroDuplicati = listaConDuplicati.size() - insiemeSenzaDuplicati.size();
			mappa2.put(regione, numeroDuplicati);
		}	
		return mappa2;
	}

	private String regioneMassima(Map<String, Integer> mappa) {
		String regioneMassima = null;
		Integer occorrenzeMassime = 0;
		for(String regione : mappa.keySet()) {
			Integer occorrenzeRegione = mappa.get(regione);
			if(occorrenzeRegione > 1) {
				if(regioneMassima == null || occorrenzeRegione > occorrenzeMassime) {
					regioneMassima = regione;
					occorrenzeMassime = occorrenzeRegione;
				}	
			}
		}
		return regioneMassima;		
	}

	public Set<String> dueRegioniConPiuOmonimi() {
		Set<String> insieme = new HashSet<String>();
		Map<String, Integer> mappa = this.mappaRegioniNumeroDuplicati();
		for(int i = 0; i < 2; i++) {
			String regione = regioneMassima(mappa);
			if(regione != null) {
				insieme.add(regione);
				mappa.remove(regione);	
			}
		}
		logger.debug("INSIEME:" + insieme.toString());
		return insieme;
	}

	public Universita universitaConPiuFuorisede() {
		Universita universitaMassima = null;
		double percentualeMassima = 0;
		for(Universita universita : this.listaUniversita) {
			double percentualeRegione = universita.percentualeFuorisede();
			logger.debug("Percentule universita: " + universita.toString() + "e' " + percentualeRegione);
			if(universitaMassima == null || percentualeRegione > percentualeMassima) {
				universitaMassima = universita;
				percentualeMassima = percentualeRegione;
			}
		}
		return universitaMassima;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Universita universita : this.listaUniversita) {
			sb.append(universita.toString());
		}
		return sb.toString();
	}
}