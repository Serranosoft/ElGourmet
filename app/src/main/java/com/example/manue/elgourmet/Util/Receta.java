package com.example.manue.elgourmet.Util;

import java.io.Serializable;

public class Receta implements Serializable {


    private String imagenUrl;
    private String nombreReceta;
<<<<<<< HEAD
    private int tiempo;
    private int porciones;
    private String url;

    public Receta(String imagenUrl, String nombreReceta, int tiempo, int porciones, String url) {
        this.imagenUrl = imagenUrl;
        this.nombreReceta = nombreReceta;
        this.tiempo= tiempo;
        this.porciones = porciones;
=======
    private String instruccionesReceta;
    private String[] ingredientes;
    private String url;

    public Receta(String imagenUrl, String nombreReceta, String instruccionesReceta, String[] ingredientes, String url) {
        this.imagenUrl = imagenUrl;
        this.nombreReceta = nombreReceta;
        this.instruccionesReceta = instruccionesReceta;
        this.ingredientes = ingredientes;
>>>>>>> 2d84e4012000c2234d28842114a8444d39e00c1b
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

<<<<<<< HEAD
=======
    public void setUrl(String url) {
        this.url = url;
    }

>>>>>>> 2d84e4012000c2234d28842114a8444d39e00c1b
    public String getImagenUrl() {
        return imagenUrl;
    }

<<<<<<< HEAD
=======
    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

>>>>>>> 2d84e4012000c2234d28842114a8444d39e00c1b
    public String getNombreReceta() {
        return nombreReceta;
    }

<<<<<<< HEAD
    public int getTiempo() {
        return tiempo;
    }

    public int getPorciones() {
        return porciones;
    }
=======
    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

    public String getInstruccionesReceta() {
        return instruccionesReceta;
    }

    public void setInstruccionesReceta(String instruccionesReceta) {
        this.instruccionesReceta = instruccionesReceta;
    }

    public String[] getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String[] ingredientes) {
        this.ingredientes = ingredientes;
    }

>>>>>>> 2d84e4012000c2234d28842114a8444d39e00c1b
}
