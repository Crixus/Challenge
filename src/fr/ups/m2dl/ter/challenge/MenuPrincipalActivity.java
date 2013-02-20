package fr.ups.m2dl.ter.challenge;


import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

public class MenuPrincipalActivity extends Activity {

	private Handler _mHandler = new Handler();

	// Quitte l'application
	private Runnable _stop = new Runnable() {
		public void run() {
			System.exit(RESULT_OK);
		}
	};

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

	/*public void onClickButtonQuitter(MenuItem item) {
		_mHandler.postDelayed(_stop, 0);
	}*/
	
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
            	System.exit(RESULT_OK);
            	return true;
            }
            return false;
     }

}
