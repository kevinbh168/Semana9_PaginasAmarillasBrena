package com.brena.lab09.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brena.lab09.Activities.DetailPublicacionActivity;
import com.brena.lab09.Models.Publicaciones;
import com.brena.lab09.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PublicacionesAdapter extends RecyclerView.Adapter<PublicacionesAdapter.ViewHolder>{
    private static final String TAG = PublicacionesAdapter.class.getSimpleName();

    private AppCompatActivity activity;

    private List<Publicaciones> publicaciones;
    private  Publicaciones publicacion;

    public PublicacionesAdapter(AppCompatActivity activity){
        this.activity = activity;
        publicaciones= new ArrayList<>();
    }


    public void setContacts(List<Publicaciones> publicaciones){
        this.publicaciones= publicaciones;
    }


    @NonNull
    @Override   // Qué layout va a usar y mantenerlo precargado
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_publicaciones, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override   // Cómo se va a enlazar el dato en cada item layout (ViewHolder)
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Publicaciones publicaciones=this.publicaciones.get(i);
        viewHolder.textNombre.setText(publicaciones.getNombreEmp());
        viewHolder.textDireccion.setText(publicaciones.getDireccion());
        viewHolder.textTelefono.setText("(01) "+publicaciones.getTelefono());
        final Context context=viewHolder.itemView.getContext();
        Picasso.with(context).load(publicaciones.getLogo()).into(viewHolder.imageLogo);

        // Definiendo el evento onclick
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + publicaciones);

                Intent intent = new Intent(v.getContext(), DetailPublicacionActivity.class);
                intent.putExtra("id", publicaciones.getId());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return publicaciones.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textNombre;
        private TextView textDireccion;
        private TextView textTelefono;
        private ImageView imageLogo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textNombre=itemView.findViewById(R.id.text_nombre);
            textDireccion=itemView.findViewById(R.id.text_direccion);
            textTelefono=itemView.findViewById(R.id.text_telefono);
            imageLogo=itemView.findViewById(R.id.logo_image);
        }
    }

}

