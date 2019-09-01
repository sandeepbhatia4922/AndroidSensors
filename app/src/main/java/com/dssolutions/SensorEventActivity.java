package com.dssolutions;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SensorEventActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    Sensor sensor;
    MediaPlayer mp;
    boolean change =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_event);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        int sensorType = getIntent().getIntExtra("sensorType", 5);
        System.out.println("sensorType  "+sensorType);
        switch (sensorType) {
            case 5: {
                sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
                mp = MediaPlayer.create(SensorEventActivity.this, R.raw.song);

            }
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values[0] > 40) {

            change =true;
            try {
               // mp.setDataSource("http://server6.mp3quran.net/thubti/001.mp3");
               // mp.prepare();
                mp.start();

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else
        {
            mp.pause();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}

