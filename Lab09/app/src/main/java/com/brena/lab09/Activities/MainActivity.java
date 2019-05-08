package com.brena.lab09.Activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.brena.lab09.Adapters.PublicacionesAdapter;
import com.brena.lab09.Models.Publicaciones;
import com.brena.lab09.R;
import com.brena.lab09.Repositories.PublicacionesRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AutoCompleteTextView textBusqueda;
    private ImageButton busquedaButton;
    private  ImageButton infoButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textBusqueda = findViewById(R.id.textBusqueda);
        busquedaButton = findViewById(R.id.busquedaButton);
        recyclerView = findViewById(R.id.empLista);
        infoButton=findViewById(R.id.info);


        editTextAuto();
        listRecyclerView();

        busquedaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String busqueda = textBusqueda.getText().toString();
                final List<Publicaciones> publicacions = PublicacionesRepository.busPublicaciones(busqueda);
                PublicacionesAdapter adapter = new PublicacionesAdapter(MainActivity.this);

                adapter.setContacts(publicacions);

                recyclerView.setAdapter(adapter);

                if(publicacions==null) {
                    listRecyclerView();
                    Toast.makeText(MainActivity.this,"No se encontraron resultados con: "+busqueda,Toast.LENGTH_SHORT).show();
                    textBusqueda.setText("");
                }
            }
        });

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Información");
                dialog.setMessage("Este aplicacion es una prueba\n hecha por Kevin Breña\n Tecsup 2019");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

        checkAllPermissions();
    }

    private static final int PERMISSIONS_REQUEST = 100;
    private static String[] PERMISSIONS_LIST = new String[]{Manifest.permission.INTERNET,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.SEND_SMS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
    };

    private void checkAllPermissions() {
        boolean permissionRequired = false;
        for (String permission : PERMISSIONS_LIST) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
                permissionRequired = true;
        }
        if (permissionRequired) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_LIST, PERMISSIONS_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST: {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, PERMISSIONS_LIST[i] + " permissions declined!", Toast.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                finishAffinity();
                            }
                        }, Toast.LENGTH_LONG);
                    }
                }
            }
        }
    }

    public void listRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PublicacionesAdapter adapter = new PublicacionesAdapter(this);

        final List<Publicaciones> publicacions = PublicacionesRepository.getPublicaciones();

        adapter.setContacts(publicacions);

        recyclerView.setAdapter(adapter);
    }

    public void editTextAuto(){
        String[] words=PublicacionesRepository.obtenerEmpresa();
        ArrayAdapter<String> adapText=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,words);
        textBusqueda.setThreshold(1);
        textBusqueda.setAdapter(adapText);
    }
}
