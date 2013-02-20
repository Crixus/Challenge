package fr.ups.m2dl.ter.challenge;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.view.Menu;

public class MainActivity extends Activity {
	
	static Vibrator vi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		MainActivity.vi = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		Intent intent = new Intent(MainActivity.this, MenuPrincipalActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

}
