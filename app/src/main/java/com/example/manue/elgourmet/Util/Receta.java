package com.example.manue.elgourmet.Util;

import java.io.Serializable;

public class Receta implements Serializable {

    private String imagenUrl;
    private String nombreReceta;
    private String instruccionesReceta;
    private String[] ingredientes;
    private String url;

    public Receta(String imagenUrl, String nombreReceta, String instruccionesReceta, String[] ingredientes, String url) {
        this.imagenUrl = imagenUrl;
        this.nombreReceta = nombreReceta;
        this.instruccionesReceta = instruccionesReceta;
        this.ingredientes = ingredientes;
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
}
