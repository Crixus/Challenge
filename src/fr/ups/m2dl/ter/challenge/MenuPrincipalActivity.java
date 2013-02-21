package fr.ups.m2dl.ter.challenge;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MenuPrincipalActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_principal);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu_principal, menu);
		return true;
	}
	
	public void onClickButtonSensorAccelerometerExemple(View view) {
		Intent intent = new Intent(MenuPrincipalActivity.this, SensorAccelerometerExempleActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void onClickButtonPartieNormale(View view) {
		MainActivity.__moteur.lancerPartie(false);
		Intent intent = new Intent(MenuPrincipalActivity.this, JeuActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void onClickButtonPartieExpert(View view) {
		MainActivity.__moteur.lancerPartie(true);
		Intent intent = new Intent(MenuPrincipalActivity.this, JeuActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void onClickButtonQuitter(View view) {
		
	}
	
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_quitter:
            	System.exit(RESULT_OK);
            	return true;
            }
            return false;
     }
}
