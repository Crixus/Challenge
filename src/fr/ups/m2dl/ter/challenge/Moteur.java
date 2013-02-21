package fr.ups.m2dl.ter.challenge;

import java.util.ArrayList;
import java.util.List;

public class Moteur {

	private boolean _expert;
	private List<String> _mouvements;

	private boolean _partieEnCours;

	private int _joueurActuel;
	private int _etatJoueur; // 0 = Doit repeter / 1 = Doit enregistrer

	public Moteur() {
		_mouvements = new ArrayList<String>();
		_expert = false;
		_partieEnCours = false;
	}

	public boolean is_expert() {
		return _expert;
	}

	public void set_expert(boolean _expert) {
		this._expert = _expert;
	}

	public List<String> get_mouvements() {
		return _mouvements;
	}

	public void set_mouvements(List<String> _mouvements) {
		this._mouvements = _mouvements;
	}

	public boolean is_partieEnCours() {
		return _partieEnCours;
	}

	public void set_partieEnCours(boolean _partieEnCours) {
		this._partieEnCours = _partieEnCours;
	}

	public int get_joueurActuel() {
		return _joueurActuel;
	}

	public void set_joueurActuel(int _joueurActuel) {
		this._joueurActuel = _joueurActuel;
	}

	public int get_etatJoueur() {
		return _etatJoueur;
	}

	public void set_etatJoueur(int _etatJoueur) {
		this._etatJoueur = _etatJoueur;
	}

	public void lancerPartie(boolean expert) {
		_mouvements.clear();
		_expert = expert;
		_joueurActuel = 0;
		_etatJoueur = 1;
		_partieEnCours = true;
	}

	public String enregistrerMouvement() {
		String mouvement = ""; // Detecte le mouvement TODO-Ludo
		_mouvements.add(mouvement);
		_joueurActuel = (_joueurActuel + 1) % 2;
		_etatJoueur = 0;
		return mouvement;
	}

	public boolean repeterMouvements() {
		int cpt = 0;
		while (_mouvements.size() != 0) {
			String mouvement = ""; // Detecte le mouvement TODO-Ludo
			if (!mouvement.equals(_mouvements.get(cpt))) {
				terminerPartie();
				return false;
			}
			cpt++;
		}
		_etatJoueur = 1;
		return true;
	}

	public void terminerPartie() {
		_partieEnCours = false;
	}

}
