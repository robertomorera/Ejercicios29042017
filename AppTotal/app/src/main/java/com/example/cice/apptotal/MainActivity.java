package com.example.cice.apptotal;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter= new IntentFilter();
        intentFilter.addAction("SERVICIO_TERMINADO");

        BroadCastReceiver br=new BroadCastReceiver();
        registerReceiver(br,intentFilter); //asociamos al receiver la señal "SERVICIO_TERMINADO".


        Intent intent_service=new Intent(this,ChequeoService.class);
        startService(intent_service);

    }
}
