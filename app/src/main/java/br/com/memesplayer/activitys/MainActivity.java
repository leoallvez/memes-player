package br.com.memesplayer.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import br.com.memesplayer.R;
import services.MainService;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBox;
    private SeekBar seekBar;
    private TextView textView;
    public SharedPreferences.Editor editor;

    public static final String MY_PREFS_NAME = "seekBarPrefs";


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBox = (CheckBox) findViewById(R.id.checkbox_cheese);
        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        textView = (TextView) findViewById(R.id.textView1);

        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        //editor.putInt("seebar_value", 0);
        textView.setText("Sensibilidade acelerômetro: " + seekBar.getProgress() + "/" + seekBar.getMax());


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 7;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                //editor.putInt("seebar_value", progress);
                //Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
                //editor.apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView.setText("Sensibilidade acelerômetro: " + progress + "/" + seekBar.getMax());
                editor.putInt("seebar_value", progress);
                //Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
                editor.apply();
            }
        });


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //is checkBox checked?
                if (((CheckBox) v).isChecked()) {
                    startService(new Intent(MainActivity.this, MainService.class));
                    Toast.makeText(MainActivity.this,
                            "Serviço ativado :)", Toast.LENGTH_LONG).show();
                }else{
                    stopService(new Intent(MainActivity.this, MainService.class));
                    Toast.makeText(MainActivity.this,
                            "Serviço desativado :(", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
