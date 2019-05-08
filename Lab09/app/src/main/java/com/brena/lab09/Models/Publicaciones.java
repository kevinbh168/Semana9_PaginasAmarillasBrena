package com.brena.lab09.Models;


public class Publicaciones {

    private String nombreEmp;
    private String logo;
    private String informacion;
    private String rubro;
    private String telefono;
    private double coordenadasLtn;
    private double coordenadasLgn;
    private String direccion;
    private String pagWeb;
    private int id;

    public void publicaciones(){

    }

    public Publicaciones(String pagWeb,int id, String nombreEmp, String logo, String informacion, String rubro, String telefono, double coordenadasLtn,double coordenadasLgn,String direccion) {
        this.pagWeb=pagWeb;
        this.id=id;
        this.coordenadasLtn=coordenadasLtn;
        this.coordenadasLgn=coordenadasLgn;
        this.nombreEmp = nombreEmp;
        this.logo = logo;
        this.informacion = informacion;
        this.rubro = rubro;
        this.telefono = telefono;
        this.direccion=direccion;
    }

    public double getCoordenadasLtn() {
        return coordenadasLtn;
    }

    public void setCoordenadasLtn(int coordenadasLtn) {
        this.coordenadasLtn = coordenadasLtn;
    }

    public double getCoordenadasLgn() {
        return coordenadasLgn;
    }

    public void setCoordenadasLgn(int coordenadasLgn) {
        this.coordenadasLgn = coordenadasLgn;
    }

    public String getPagWeb() {
        return pagWeb;
    }

    public void setPagWeb(String pagWeb) {
        this.pagWeb = pagWeb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEmp() {
        return nombreEmp;
    }

    public void setNombreEmp(String nombreEmp) {
        this.nombreEmp = nombreEmp;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
