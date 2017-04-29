package com.example.cice.seleccioncontacto;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(intent,100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Cursor cursor=null;
        String phoneNo=null;
        String name=null;
        Uri uri=null;

        if(resultCode==RESULT_OK){
            try{

                uri=data.getData();
                Log.d("MENSAJE","URI_CONTACTO= "+uri.toString());
                //CONSULTO EL CONTECT PROVIDER
                cursor=getContentResolver().query(uri,null,null,null,null);
                cursor.moveToFirst();
                //Obtenemos el indice de las columnas.
                int ncolNumber=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                int ncolName=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                phoneNo=cursor.getString(ncolNumber);
                name=cursor.getString(ncolName);
                cursor.close();
                TextView tvNombre=(TextView)findViewById(R.id.nombre_contacto);
                TextView tvNumero=(TextView)findViewById(R.id.telefono_contacto);
                tvNombre.setText(name);
                tvNumero.setText(phoneNo);

            }catch(Exception e){
                Log.e("MENSAJE","ERROR",e);
            }
        }else{
            Log.e("MENSAJE","ERROR el usuario cancelo la selección de contacto");
        }

    }
}
