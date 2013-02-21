package fr.ups.m2dl.ter.challenge;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class GameOverActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_over);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_game_over, menu);
		return true;
	}
	
	public void onClickButtonRejouer(View view) {
		MainActivity.__moteur = new Moteur();
		Intent intent = new Intent(GameOverActivity.this, MenuPrincipalActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void onClickButtonMenuPrincipal(View view) {
		System.exit(RESULT_OK);
		finish();
	}

}
