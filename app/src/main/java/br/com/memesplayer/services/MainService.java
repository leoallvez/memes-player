package br.com.memesplayer.services;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import br.com.memesplayer.R;
import br.com.memesplayer.activitys.MainActivity;
import br.com.memesplayer.interfaces.IAccelerometer;
import br.com.memesplayer.models.SensorListen;

public class MainService extends Service implements IAccelerometer {

    private long lastUpdate;
    private MediaPlayer mediaPlayer;
    private SensorEventListener listen;
    private SensorManager sensorManager;
    public SharedPreferences prefs;

    @Override
    public IBinder onBind(Intent intent) { return null; }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        prefs = getSharedPreferences(MainActivity.MY_PREFS_NAME, MODE_PRIVATE);

        sensorManager = (SensorManager) getApplicationContext()
                .getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();
        listen = new SensorListen(this);

        Sensor accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(listen, accel, SensorManager.SENSOR_DELAY_NORMAL);

        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void getAccelerometer(SensorEvent event) {
        float[] values = event.values;

        int value = prefs.getInt("seebar_value", 0);
        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
        long actualTime = System.currentTimeMillis();
        if (accelationSquareRoot >= value)
        {
            if (actualTime - lastUpdate < 2000) {
                return;
            }

            lastUpdate = actualTime;
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.nervoso);
            mediaPlayer.start();
        }
    }

    @Override
    public void onDestroy() {
        sensorManager.unregisterListener(listen);
        super.onDestroy();
    }
}