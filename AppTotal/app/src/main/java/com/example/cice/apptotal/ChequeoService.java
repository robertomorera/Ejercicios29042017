package com.example.cice.apptotal;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class ChequeoService extends Service {
    public ChequeoService() {
    }

    private String mensaje_remoto;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
      return null;
    }

    private String obtenerMensajeRemoto(){

        String str_dev=null;

        str_dev=(Math.random()>0.5)? "HOLA AMORCITO": "";

        return str_dev; //aquí sería donde llamará al AsynkTask.get()
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Aquí iría el código que ejecuta el servicio
        Log.d("MENSAJE","Servicio iniciado");
        Log.d("MENSAJE","StartId=" +startId );

        mensaje_remoto=obtenerMensajeRemoto();
        stopSelf(startId); //Para matar al servicio.

        return Service.START_NOT_STICKY; //EN CASO DE QUE EL SISTEMA MATA AL SERVICIO, LE INFORMO QUE NO HACE FALTA QUE LO REINICIE.
    }

    @Override
    public void onDestroy() {
        //Código al finalizar el servicio.
        Log.d("MENSAJE","Servicio detenido");
        super.onDestroy();
        Intent intent_receiver=new Intent("SERVICIO_TERMINADO");
        intent_receiver.putExtra("MENSAJE",mensaje_remoto);
        sendBroadcast(intent_receiver);
    }
}
