package fr.ups.m2dl.ter.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.util.Log;

public class Moteur {

	private boolean _expert;
	private List<String> _mouvements;

	private boolean _partieEnCours;

	private int _joueurActuel;
	private int _etatJoueur; // 0 = Doit repeter / 1 = Doit enregistrer
	
	private int _mouvementEnCours;

	public Moteur() {
		_mouvements = new ArrayList<String>();
		_expert = false;
		_partieEnCours = false;
		_mouvementEnCours = _mouvements.size();
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
		Log.v("INFO", "Partie lancée");
		_mouvements.clear();
		_mouvementEnCours = _mouvements.size();
		_expert = expert;
		_joueurActuel = 0;
		_etatJoueur = 1;
		_partieEnCours = true;
		Log.v("INFO", "Partie initialisée");
	}
	
	public void prochainMouvement(String mouvement) {
		Log.v("INFO", "Prochain mouvement !");
		Log.v("INFO", "Mouvements dans liste : " + Arrays.toString(_mouvements.toArray()));
		if (_partieEnCours) {
			if (_etatJoueur == 0) {
				Log.v("INFO", "REPETER !");
				repeterMouvements(mouvement);
			} else {
				Log.v("INFO", "ENREGISTRER !");
				enregistrerMouvement(mouvement);
			}
		} else {
			Log.v("INFO", "PERDU !");
		}
	}
	
	public String mouvementARepeter() {
		return !_mouvements.isEmpty() ? _mouvements.get(_mouvementEnCours) : "";
	}
	
	public int joueurActuel() {
		return _joueurActuel + 1;
	}

	public String enregistrerMouvement(String mouvement) {
		Log.v("INFO", "Avant : "+ Arrays.toString(_mouvements.toArray()));
		Log.v("INFO", "Ajout de mouvement "+mouvement);
		_mouvements.add(mouvement);
		Log.v("INFO", "Apres : "+ Arrays.toString(_mouvements.toArray()));
		_joueurActuel = (_joueurActuel + 1) % 2;
		_etatJoueur = 0;
		_mouvementEnCours = 0;
		return mouvement; 
	}

	public void repeterMouvements(String mouvement)  {
		Log.v("INFO", "Liste : "+ Arrays.toString(_mouvements.toArray()));
		Log.v("INFO", "Mouvement à repeter : " + _mouvements.get(_mouvementEnCours));
		if (mouvement.equals(_mouvements.get(_mouvementEnCours))) {
			if (_mouvementEnCours == _mouvements.size() - 1) {
				_mouvementEnCours = 0;
				_etatJoueur = 1;
			} else {
				_mouvementEnCours++;
			}
		} else {
			terminerPartie();
		}
	}

	public void terminerPartie() {
		_partieEnCours = false;
	}

}
