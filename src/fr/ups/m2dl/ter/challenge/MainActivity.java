package fr.ups.m2dl.ter.challenge;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.view.Menu;

public class MainActivity extends Activity {
	
	static Vibrator __vi;
	
	static Moteur __moteur;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		MainActivity.__vi = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		MainActivity.__moteur = new Moteur();
		
		Intent intent = new Intent(MainActivity.this, MenuPrincipalActivity.class);
		startActivity(intent);
		finish();
	}
	
	@Override
	public void onPause() {
		//rien pour le moment
	}
	
	@Override
	public void onResume() {
		//rien pour le moment
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
	
	public Moteur getMoteur() {
		return __moteur;
	}

}
