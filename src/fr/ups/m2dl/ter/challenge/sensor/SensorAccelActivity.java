package fr.ups.m2dl.ter.challenge.sensor;

import fr.ups.m2dl.ter.challenge.R;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.util.Log;

	public class SensorAccelActivity extends Activity  implements SensorEventListener {

		private SensorManager mSensorManager;
		private Sensor mSensor;
		private String currentMouvement = "No Mouvement";
		private Boolean pause = false;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_sensor_accelerometer_exemple);
			mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
			mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
			mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_FASTEST);
		}

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}

		private void checkMove(double x, double y, double z){
			
			if(pause) return;
			
			if (x > 8) {
				currentMouvement = "Gauche";
				Log.v("Mouvement changed", currentMouvement); 
			}
			
			if (x < -8) {
				currentMouvement = "Droite";
				Log.v("Mouvement changed", currentMouvement);
			}
			
			if (y > 8) {
				currentMouvement = "Bas";
				Log.v("Mouvement changed", currentMouvement);
			}
			
			if (y < -8) {
				currentMouvement = "Haut";
				Log.v("Mouvement changed", currentMouvement);
			}
		}
		
		public String getMove(){
			resetMove();
			while(currentMouvement == "No Mouvement"){}
			return currentMouvement;
		}
		
		public void resetMove(){
			currentMouvement = "No Mouvement";
		}
		
		public void setPause(Boolean pause){
			this.pause = pause;
		}
		
		public void onSensorChanged(SensorEvent event) {
			if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
				
				double x = event.values[0];
				double y = event.values[1];
				double z = event.values[2];
				
			
//				textview.setText("" + x + "\n" + y + "\n" + z);
				checkMove ( x,  y,  z);
//				
//				if (x > 8) {
//					TextView result = (TextView) findViewById(R.id.result);
//					result.setText("Gauche");
//					
//				}
//				
//				if (x < -8) {
//					TextView result = (TextView) findViewById(R.id.result);
//					result.setText("Droite");
//				}
//				
//				if (y > 8) {
//					TextView result = (TextView) findViewById(R.id.result);
//					result.setText("Bas");
//				}
//				
//				if (y < -8) {
//					TextView result = (TextView) findViewById(R.id.result);
//					result.setText("Haut");
//				}
				
			}
		}

	}

