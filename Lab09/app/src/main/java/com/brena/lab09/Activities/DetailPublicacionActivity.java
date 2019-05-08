package com.brena.lab09.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.brena.lab09.Models.Publicaciones;
import com.brena.lab09.R;
import com.brena.lab09.Repositories.PublicacionesRepository;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import static com.brena.lab09.R.id.call_button;
import static com.brena.lab09.R.id.website_button;

public class DetailPublicacionActivity extends FragmentActivity implements OnMapReadyCallback {
    private Integer id;

    private ImageView logoImagen;
    private TextView textRubro;
    private TextView textNombre;
    private TextView textInfo;
    private TextView textDireccion;
    private ImageView callButton;
    private ImageView website_button;
    private ImageView smsbutton;
    private ImageView emailbutton;
    private ImageView sharebutton;

    private static final int PERMISSION_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_publicacion);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        logoImagen = findViewById(R.id.logo_image);
        textRubro = findViewById(R.id.text_rubro);
        textNombre = findViewById(R.id.text_nombre);
        textInfo = findViewById(R.id.text_info);
        textDireccion = findViewById(R.id.text_direccion);
        callButton = findViewById(R.id.call_button);
        website_button = findViewById(R.id.website_button);
        smsbutton = findViewById(R.id.sms_button);
        emailbutton=findViewById(R.id.email_button);
        sharebutton=findViewById(R.id.share_button);

        this.id = getIntent().getExtras().getInt("id");

        final Publicaciones publicaciones = PublicacionesRepository.busquedaPublicaciones(id);

        if (publicaciones != null) {
            Picasso.with(this).load(publicaciones.getLogo()).into(logoImagen);
            textNombre.setText(publicaciones.getNombreEmp());
            textRubro.setText(publicaciones.getRubro());
            textDireccion.setText(publicaciones.getDireccion());
            textInfo.setText(publicaciones.getInformacion());



            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    call(publicaciones.getTelefono());
                }
            });

            website_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view(publicaciones.getPagWeb());
                }
            });
            smsbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sms(publicaciones.getTelefono(), publicaciones.getNombreEmp());
                }
            });
            emailbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    send_email(publicaciones.getNombreEmp()+"@gmail.com");
                }
            });
            sharebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    share(publicaciones.getNombreEmp(),publicaciones.getPagWeb());
                }
            });
        }

    }


    private void call(String number) {

        // Verificar si tengo permiso de CALL_PHONE
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_REQUEST);
            return;
        }

        String phonenumber = "(01)" + number;


        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phonenumber));

        // Si el intent puede ser resuelto por el SO se iniciará
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void sms(String number2, String tienda) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST);
            return;
        }

        Uri uri = Uri.parse("smsto:" + number2);
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", "Hola ustedes son, " + tienda);
        startActivity(it);

    }

    private void view(String pagina) {
        String url = pagina;


        if (!url.startsWith("http://") || !url.startsWith("https://")) {
            url = "https://" + url;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void send_email(String email){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL  , email);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Consulta");
        intent.putExtra(Intent.EXTRA_TEXT   , "Quisiera saber mas información porfavor...");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(DetailPublicacionActivity.this, "No hay aplicaciones instaladas para correo.", Toast.LENGTH_SHORT).show();
        }
    }

    private void share(String emp,String pag){

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Te envio una pagina muy interesante de la empresa "+emp+".\n Ingresa aqui :www."+pag);
        startActivity(Intent.createChooser(intent, "Share with"));

    }

    GoogleMap map;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.id = getIntent().getExtras().getInt("id");

        final Publicaciones publicaciones = PublicacionesRepository.busquedaPublicaciones(id);

        map=googleMap;
        LatLng lugar=new LatLng(publicaciones.getCoordenadasLtn(),publicaciones.getCoordenadasLgn());
        map.addMarker(new MarkerOptions().position(lugar).title("Megaplaza"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(lugar,16));
    }
}
