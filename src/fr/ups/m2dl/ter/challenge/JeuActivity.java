package fr.ups.m2dl.ter.challenge;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class JeuActivity extends Activity implements SensorEventListener {
	
	private SensorManager mSensorManager; // Sensor Manager
	private Sensor mSensor; // Sensor (ACCELEROMETER)
	private boolean _estInitialise = false; // L'utilisateur est revenu à la position environ plate

	MediaPlayer _mp;
	
	private ImageView _fleche;
	private TextView _tvJoueur;
	private TextView _tvAction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jeu);

		mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_FASTEST);
		
		_fleche = (ImageView) findViewById(R.id.fleche);
		_tvJoueur = (TextView) findViewById(R.id.label_joueur);
		_tvAction = (TextView) findViewById(R.id.label_action);
		
		mettreAJourFenetre();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_jeu, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_FASTEST);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}
	
	@Override
	public void onBackPressed(){
		Intent intent = new Intent(JeuActivity.this, MenuPrincipalActivity.class);
		startActivity(intent);
		finish();
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			String mouvement; // Le mouvement effectué

			double x = event.values[0]; // Recupération x
			double y = event.values[1]; // Recupération y

			if (!_estInitialise && x < 2 && x > -2 && y < 2 && y > -2) { // Plat
				_estInitialise = true;
				mouvement = "Autre";
			} else if (x > 7) { // Gauche
				mouvement = "Gauche";
			} else if (x < -7) { // Droite
				mouvement = "Droite";
			} else if (y > 7) { // Bas
				mouvement = "Bas";
			} else if (y < -7) { // Haut
				mouvement = "Haut";
			} else { // Autre
				mouvement = "Autre";
			}

			if (_estInitialise && !mouvement.equals("Autre")) {
				// L'utilisateur est revenu plat
				// L'utilisateur fait un mouvement intéressant (G, D, H, B)
				_estInitialise = false; // Il devrait revenir plat

				MainActivity.__moteur.prochainMouvement(mouvement); // Prise en compte mouvement dans moteur
				
				if (!MainActivity.__moteur.is_partieEnCours()) { // Si partie terminée (erreur)
					// On joue le son erreur et passage à Game Over
					_mp = MediaPlayer.create(this, R.raw.error);
					_mp.start();
					Intent intent = new Intent(JeuActivity.this, GameOverActivity.class);
					startActivity(intent);
					finish();
				} else {
					// On joue le son OK, on met à jour la fenetre
					_mp = MediaPlayer.create(this, R.raw.beep_seven);
					_mp.start();
					mettreAJourFenetre();
				}
				
			}
		}
	}
	
	public void mettreAJourFenetre() {
		mettreAJourAction();
		mettreAJourFleche();
		mettreAJourLabelJoueur();
	}
	
	public void mettreAJourAction() {
		_tvAction.setText(MainActivity.__moteur.action());
	}
	
	public void mettreAJourFleche() {
		String action = MainActivity.__moteur.action();
		boolean expert = MainActivity.__moteur.is_expert();
		
		if (action.equals("REPRODUIRE") && !expert) {
			String mouvementReprod = MainActivity.__moteur.mouvementARepeter();
			
			if (mouvementReprod.equals("Haut")) {
				_fleche.setImageResource(R.drawable.haut);
			} else if (mouvementReprod.equals("Bas")) {
				_fleche.setImageResource(R.drawable.bas);
			}else if (mouvementReprod.equals("Gauche")) {
				_fleche.setImageResource(R.drawable.gauche);
			}else  {
				_fleche.setImageResource(R.drawable.droite);
			}
			_fleche.setVisibility(0);
		}
		else {
			_fleche.setVisibility(8);
		}
	}
	
	public void mettreAJourLabelJoueur() {
		String label_joueur = "Joueur " + MainActivity.__moteur.joueurActuel();
		_tvJoueur.setText(label_joueur);
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			System.exit(RESULT_OK);
			return true;
		case R.id.menu_settings2:
			Intent intent = new Intent(JeuActivity.this, MenuPrincipalActivity.class);
			startActivity(intent);
			finish();
			return true;
		}
		return false;
	}
}
