package fr.ups.m2dl.ter.challenge;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class JeuActivity extends Activity {
	private int etat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_jeu);
		jouer();
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
				setContentView(R.layout.activity_jeu_enregistrer);
//				MainActivity.__moteur.enregistrerMouvement();
//				TextView tvEnrg = (TextView) findViewById(R.id.textView1);
//				tvEnrg.setText("Joueur " + MainActivity.__moteur.get_joueurActuel() + " " + "faites un movement");
//				tvEnrg.setText(MainActivity.__moteur.get_mouvements());
				break;
			}
		}
	
		
	}
}
