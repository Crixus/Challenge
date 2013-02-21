package fr.ups.m2dl.ter.challenge;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class JeuActivity extends Activity implements SensorEventListener {
	
	private boolean _estInitialise = false;
	
	private SensorManager mSensorManager;
	private Sensor mSensor;
	
	private int _etat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_FASTEST);
		super.onCreate(savedInstanceState);
		afficherFenetre();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_jeu, menu);
		return true;
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			
			double x = event.values[0];
			double y = event.values[1];
			double z = event.values[2];
			
			String mouvement = "";
			
			if (x > 8) {
				mouvement = "Gauche";
			}
			
			if (x < -8) {
				mouvement = "Droite";
			}
			
			if (y > 8) {
				mouvement = "Bas";
			}
			
			if (y < -8) {
				mouvement = "Haut";
			}
			
			if (!_estInitialise && x < 2 && x > -2 && y < 2 && y > -2) {
				Log.v("INFO", "INITIALISATION !");
				_estInitialise = true;
			}
			
			if (!mouvement.equals("") && _estInitialise) {
				_estInitialise = false;
				mouvement(mouvement);
			}
		}
	}

	public void afficherFenetre() {
		_etat = MainActivity.__moteur.get_etatJoueur();
		int joueur = MainActivity.__moteur.joueurActuel();
		switch(_etat) {
		case 0:
			setContentView(R.layout.activity_jeu_repeter);	
			TextView tvRep = (TextView) findViewById(R.id.textViewRep);
			tvRep.setText("Joueur " + joueur + " refaites le mouvement");
			break;
		case 1:
			setContentView(R.layout.activity_jeu_enregistrer);
			TextView tvEnr = (TextView) findViewById(R.id.textViewEng);
			tvEnr.setText("Joueur " + joueur + " faites un mouvement");
			
			String mouvement = MainActivity.__moteur.mouvementARepeter();
			TextView tvEngMvt = (TextView) findViewById(R.id.textViewEngMvt);
			tvEngMvt.setText(mouvement);
			break;
		}
	}

	public void mouvement(String mouvement) {
		System.out.println(mouvement);
		MainActivity.__moteur.prochainMouvement(mouvement);
		if (!MainActivity.__moteur.is_partieEnCours()) {
			Intent intent = new Intent(JeuActivity.this, JeuActivity.class);
			startActivity(intent);
			finish();
		}
		afficherFenetre();
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
}
