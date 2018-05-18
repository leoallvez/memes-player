package br.com.memesplayer.models;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import br.com.memesplayer.interfaces.IAccelerometer;

public class SensorListen implements SensorEventListener {

    private IAccelerometer accelerometer;

    public SensorListen(IAccelerometer accelerometer){
        this.accelerometer = accelerometer;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelerometer.getAccelerometer(event);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
