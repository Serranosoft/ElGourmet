package com.example.manue.elgourmet.Util;

import java.io.Serializable;

public class Receta implements Serializable {


    private String imagenUrl;
    private String nombreReceta;
    private int tiempo;
    private int porciones;
    private String url;

    public Receta(String imagenUrl, String nombreReceta, int tiempo, int porciones, String url) {
        this.imagenUrl = imagenUrl;
        this.nombreReceta = nombreReceta;
        this.tiempo = tiempo;
        this.porciones = porciones;
        this.url = url;
    }


    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public int getTiempo() {
        return tiempo;
    }

    public int getPorciones() {
        return porciones;
    }
    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }
}
