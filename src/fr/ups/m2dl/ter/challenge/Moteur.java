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
		_mouvements.clear();
		_mouvementEnCours = _mouvements.size();
		_expert = expert;
		_joueurActuel = 0;
		_etatJoueur = 1;
		_partieEnCours = true;
	}
	
	public void prochainMouvement(String mouvement) {
		if (_partieEnCours) {
			if (_etatJoueur == 0) {
				repeterMouvements(mouvement);
			} else {
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
		_mouvements.add(mouvement);
		_joueurActuel = (_joueurActuel + 1) % 2;
		_etatJoueur = 0;
		_mouvementEnCours = 0;
		return mouvement; 
	}

	public void repeterMouvements(String mouvement)  {
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
	
	public void recapitulatifPartie() {
		String string = "";
		if (!_partieEnCours) {
			string += "La partie est terminée !";
		} else {
			string += "C'est au tour de joueur " + joueurActuel() + "\n";
			string += "Il faut " + ((_etatJoueur == 1) ? "Ajouter un mouvement" : "Répeter les mouvements") + "\n";
			string += "Les mouvements enregistrés sont : " + Arrays.toString(_mouvements.toArray()) + "\n";
			if (_etatJoueur == 0) {
				string += "Le mouvement à reproduire est : " + mouvementARepeter() + "\n";
			} else {
				string += "Il faut ajouter un mouvement\n";
			}
		}
		System.out.println(string);
		Log.v("INFO", string);				
	}

}
