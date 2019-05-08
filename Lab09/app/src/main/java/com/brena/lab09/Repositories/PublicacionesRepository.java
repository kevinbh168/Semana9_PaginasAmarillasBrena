package com.brena.lab09.Repositories;
import android.util.Log;

import com.brena.lab09.Models.Publicaciones;

import java.util.ArrayList;
import java.util.List;

public class PublicacionesRepository {

    private static ArrayList<Publicaciones> publiEmp;
    private static String info="AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    static{
        publiEmp=new ArrayList<>();
        publiEmp.add(new Publicaciones("norkys.pe/",1,"Norky's","http://plazanorte.pe/cache/wp-content/uploads/2016/04/norkys-3.jpg",info, "RESTAURANTE","555 5555",-11.934507, -77.047503,"Av. Tupac Amaru 949"));
        publiEmp.add(new Publicaciones("www.pardoschicken.pe/promociones/",2,"Pardos","https://www.pardoschicken.pe/wp-content/uploads/bellavista.jpeg",info, "RESTAURANTE","666 6666",-11.994411, -77.060482,"C.C. Mega Plaza – Independencia"));
        publiEmp.add(new Publicaciones("rokys.com/",3,"Rokys","https://www.peru-retail.com/wp-content/uploads/ROKY.jpg",info, "RESTAURANTE","999 9999",-11.942939, -77.060196,"Av. Guillermo Gerardino 107"));
        publiEmp.add(new Publicaciones("www.kfc.com.pe/Online",4,"KFC","https://portal.andina.pe/EDPfotografia3/Thumbnail/2017/06/15/000428367W.jpg",info, "RESTAURANTE","999 9988",-11.933289, -77.047675,"Av. Universitaria 6832"));
        publiEmp.add(new Publicaciones("www.promart.pe/",5,"Promart","https://img.gestion.pe/files/article_content_ec_fotos/uploads/2017/11/07/5a027265372b0.jpeg",info, "CONSTRUCCIÓN","999 9911",-11.940341, -77.069597," Jiron Centenario 1561"));
        publiEmp.add(new Publicaciones("www.maestro.com.pe/",6,"Maestro","https://img.elcomercio.pe/files/article_content_ec_fotos/uploads/2017/03/28/58db2b954fdd7.jpeg",info, "CONSTRUCCIÓN","999 9142",-11.975789, -77.065537,"Aux. Panamericana Nte. 3515"));
        publiEmp.add(new Publicaciones("www.sodimac.com.pe/sodimac-pe/",7,"Sodimac","https://www.sodimac.com.pe/static/categorias/contenidoEstatico/masdesodimac/nuestras-tiendas/img/imagen-modal.jpg",info, "CONSTRUCCIÓN","159 6321",-11.994512, -77.059964,"Av. Tomas Valle 15311"));
        publiEmp.add(new Publicaciones("www.senati.edu.pe/",8,"Senati","https://cde.publimetro.e3.pe/ima/0/0/1/9/4/194618.jpg",info, "EDUCACIÓN","997 8523",-11.999202, -77.062305,"Av. Globo Terráqueo 34540"));
        publiEmp.add(new Publicaciones("www.tecsup.edu.pe/",9,"TECSUP","https://www.tecsup.edu.pe/sites/default/files/generic_item/image/lima-galeria.png",info, "EDUCACIÓN","912 1587",-12.044251, -76.952735,"Av. Cascanueces 22219"));
    }

    public static List<Publicaciones> getPublicaciones(){
        return publiEmp;
    }

    public static Publicaciones busquedaPublicaciones(int id){
        for (Publicaciones pemp: publiEmp) {
            if(pemp.getId()==id){
                return pemp;
            }
        }
        return null;
    }

    public static List<Publicaciones> busPublicaciones(String nombre){
        for (Publicaciones pemp:publiEmp)
            if (pemp.getNombreEmp().equalsIgnoreCase(nombre)) {
                ArrayList<Publicaciones> publiEncontrado = new ArrayList<>();
                publiEncontrado.add(new Publicaciones(pemp.getPagWeb(), pemp.getId(), pemp.getNombreEmp(), pemp.getLogo(), pemp.getInformacion(), pemp.getRubro(), pemp.getTelefono(), pemp.getCoordenadasLtn(), pemp.getCoordenadasLgn(), pemp.getDireccion()));
                return publiEncontrado;
            }
        return null;
    }

    public static String[] obtenerEmpresa(){
        String[] empresas=new String[publiEmp.size()];
         for (int i=0;i<publiEmp.size();i++){
             empresas[i]=publiEmp.get(i).getNombreEmp();
             Log.d("empresa"+(i+1),empresas[i]);
         }
         return empresas;
    }
}
