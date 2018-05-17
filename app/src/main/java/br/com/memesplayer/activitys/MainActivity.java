package br.com.memesplayer.activitys;

import android.content.Intent;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import br.com.memesplayer.R;
import services.MainService;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBox = (CheckBox) findViewById(R.id.checkbox_cheese);

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
