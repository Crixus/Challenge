package fr.ups.m2dl.ter.challenge;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MenuPrincipalActivity extends Activity {
	
	MediaPlayer _mp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_principal);
		
		_mp = MediaPlayer.create(this, R.raw.fond_appli);
		_mp.setLooping(true);
		_mp.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu_principal, menu);
		return true;
	}
	
	@Override
	public void onPause() {
		super.onPause();
		_mp.pause();
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    _mp.start();
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
		System.exit(RESULT_OK);
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
