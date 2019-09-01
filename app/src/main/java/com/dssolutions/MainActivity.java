package com.dssolutions;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SensorAdapter adapter;
    SensorManager sensorManager;
    List<Sensor> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorList();
        recyclerView = findViewById(R.id.rv_sensors);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setHasFixedSize(true);


        if(sensorList().size()>0)
        {
            adapter = new SensorAdapter(this, sensorList());
            recyclerView.setAdapter(adapter);
        }
    }

    private List<Sensor> sensorList() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        list = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : list) {
            System.out.println(sensor);
        }

        return list;
    }
}
