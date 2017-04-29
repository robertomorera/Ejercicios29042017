package com.example.cice.contentprovider;

import android.Manifest;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 700);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //TODO verificar que el permiso ha sido concedido "GRANTED"

        ContentResolver contentResolver = getContentResolver();
        Uri uri_contactos = ContactsContract.Contacts.CONTENT_URI; //content://com.android.contacts/contacts

        //Cursor cursor_contactos = contentResolver.query(uri_contactos, null, null, null, null);
        //Cursor cursor_contactos = contentResolver.query(uri_contactos, null,ContactsContract.Contacts.DISPLAY_NAME+" = 'Mama Mvl'", null, null);
        //Cursor cursor_contactos = contentResolver.query(uri_contactos, null,ContactsContract.Contacts.HAS_PHONE_NUMBER+"= 1", null, null);
        //Cursor cursor_contactos = contentResolver.query(uri_contactos, null, null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC"); //Con esto ordeno ascendentemente los contactos.
        //String [] prefijo={"M%"};
        //Cursor cursor_contactos = contentResolver.query(uri_contactos, null,ContactsContract.Contacts.DISPLAY_NAME + " LIKE ?", prefijo, null);
        //String [] columnas={ContactsContract.Contacts.DISPLAY_NAME};
        //Cursor cursor_contactos = contentResolver.query(uri_contactos,columnas, null, null, null);
        /*
        if (cursor_contactos.moveToFirst()) {
            Log.d("MENSAJE", "NUMERO DE CONTACTOS = " + cursor_contactos.getCount());

            int numColumnId = cursor_contactos.getColumnIndex(ContactsContract.Contacts._ID);
            int numColumnName = cursor_contactos.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);

            Cursor cursor_raw = null;
            Cursor cursor_data = null;

            long id_aux = 0;
            String str_aux = null;


            do {
                id_aux = cursor_contactos.getLong(numColumnId);
                str_aux = cursor_contactos.getString(numColumnName);
                Log.d("MENSAJE", "CONTACT ID = " + id_aux + " Nombre Contacto = " + str_aux);
                cursor_raw = contentResolver.query(ContactsContract.RawContacts.CONTENT_URI, null, ContactsContract.RawContacts.CONTACT_ID + " = " + id_aux, null, null);

                if (cursor_raw.moveToFirst()) {
                    do {
                        long id_raw_aux = cursor_raw.getLong(cursor_raw.getColumnIndex(ContactsContract.RawContacts._ID));
                        String nombre_cuenta = cursor_raw.getString(cursor_raw.getColumnIndex(ContactsContract.RawContacts.ACCOUNT_NAME));
                        String tipo_cuenta = cursor_raw.getString(cursor_raw.getColumnIndex(ContactsContract.RawContacts.ACCOUNT_TYPE));

                        Log.d("MENSAJE", "RAW NOMBRE CUENTA: " + nombre_cuenta + " TIPO CUENTA: " + tipo_cuenta);

                        cursor_data = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, ContactsContract.Data.RAW_CONTACT_ID + " = " + id_raw_aux, null, null);

                        if (cursor_data.moveToFirst()) {
                            do {
                                String mime_type = cursor_data.getString(cursor_data.getColumnIndex(ContactsContract.Data.MIMETYPE));
                                String data = cursor_data.getString(cursor_data.getColumnIndex(ContactsContract.Data.DATA1));

                                Log.d("MENSAJE", "DATA MIME = " + mime_type + " DATA 1: " + data);
                            } while (cursor_data.moveToNext()); //fin de detalles
                        }
                        cursor_data.close();
                    } while (cursor_raw.moveToNext());
                }
                cursor_raw.close();
            } while (cursor_contactos.moveToNext());
            cursor_contactos.close();
        }
    }*/

    Cursor phones=contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);

        while(phones.moveToNext()){
            String number=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            int type=phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
            Log.d("MENSAJE","TELEFONO= "+number + " TIPO= "+type);
        }

        phones.close();



    }

}



