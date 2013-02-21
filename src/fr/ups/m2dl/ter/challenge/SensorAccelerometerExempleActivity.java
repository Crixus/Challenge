package fr.ups.m2dl.ter.challenge;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class SensorAccelerometerExempleActivity extends Activity  implements SensorEventListener {

	private SensorManager mSensorManager;
	private Sensor mSensor;

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

	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			
			double x = event.values[0];
			double y = event.values[1];
			double z = event.values[2];
			
			TextView textview = (TextView) findViewById(R.id.accelerometer);
			
			textview.setText("" + x + "\n" + y + "\n" + z);
			
			if (x > 8) {
				TextView result = (TextView) findViewById(R.id.result);
				result.setText("Gauche");
			}
			
			if (x < -8) {
				TextView result = (TextView) findViewById(R.id.result);
				result.setText("Droite");
			}
			
			if (y > 8) {
				TextView result = (TextView) findViewById(R.id.result);
				result.setText("Bas");
			}
			
			if (y < -8) {
				TextView result = (TextView) findViewById(R.id.result);
				result.setText("Haut");
			}
			
		}
	}

}
