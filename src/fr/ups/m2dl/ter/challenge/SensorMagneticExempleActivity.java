package fr.ups.m2dl.ter.challenge;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class SensorMagneticExempleActivity extends Activity implements SensorEventListener {

	private SensorManager mSensorManager;
	private Sensor mSensor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor_magnetic_exemple);
		mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater()
		.inflate(R.menu.activity_sensor_magnetic_exemple, menu);
		return true;
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
			float[] values = event.values;
			TextView textview = (TextView) findViewById(R.id.magnetic);
			//All values are in micro-Tesla (uT) and measure the ambient magnetic field in the X, Y and Z axis.
			
			int max = (int) Math.max(Math.max(values[0], values[1]), values[2]);
			
			textview.setText("" + max);
			
			TextView result = (TextView) findViewById(R.id.result);
			
			if (max > 50) {
				result.setText("LES MILLIONS !!!");
			} else {
				result.setText("Rien");
			}
		}
	}

}
