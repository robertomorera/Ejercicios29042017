package com.example.cice.apptotal;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;

public class BroadCastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        Log.d("MENSAJE","Servicio acabado");
        String mensaje_recibido=intent.getStringExtra("MENSAJE");
        if(mensaje_recibido.equals("")){
            Log.d("MENSAJE","Servicio no me entrega mensaje");
            //TODO REPROGRAMA EL SERVICIO.
            programarAlarma(context);
        }else{
            Log.d("MENSAJE","Servici si me entrega mensaje");
            //TODO LANZAR NOTIFICACION
            lanzarNotificacion(mensaje_recibido,context);
        }
    }

    private void lanzarNotificacion(String mensaje,Context context){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Mensaje")
                .setContentText(mensaje);
        Intent intent=new Intent(context,Main2Activity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,(int)System.currentTimeMillis(),intent,PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(537,builder.build());

    }

    private void programarAlarma(Context context){

        AlarmManager alarmManager=(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Calendar calendar=Calendar.getInstance();
        long tiempo=calendar.getTimeInMillis()+60000;

        Intent intent=new Intent(context,BroadCastRecevier2.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(context,44,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP,tiempo,pendingIntent);

    }




}
