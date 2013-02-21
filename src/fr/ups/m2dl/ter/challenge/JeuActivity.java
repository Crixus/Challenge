package fr.ups.m2dl.ter.challenge;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class JeuActivity extends Activity {
	private int etat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jeu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_jeu, menu);
		return true;
	}

	public void jouer() {
		while(MainActivity.__moteur.is_partieEnCours()) {
			etat = MainActivity.__moteur.get_etatJoueur();
			switch(etat) {
			case 0:
				MainActivity.__moteur.repeterMouvements();
				
				break;
				
			case 1:
				MainActivity.__moteur.enregistrerMouvement();
				break;
			}
		}
	
		
	}
}
