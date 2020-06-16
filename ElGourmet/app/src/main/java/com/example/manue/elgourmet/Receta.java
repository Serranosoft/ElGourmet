package com.example.manue.elgourmet;

import java.io.Serializable;

public class Receta implements Serializable {

    private String imagenUrl;
    private String nombreReceta;
    private String instruccionesReceta;
    private String[] ingredientes;

    public Receta(String imagenUrl, String nombreReceta, String instruccionesReceta, String[] ingredientes) {
        this.imagenUrl = imagenUrl;
        this.nombreReceta = nombreReceta;
        this.instruccionesReceta = instruccionesReceta;
        this.ingredientes = ingredientes;
    }

    public Receta(String imagenUrl, String nombreReceta) {
        this.imagenUrl = imagenUrl;
        this.nombreReceta = nombreReceta;
    }

    public Receta(String imagenUrl, String nombreReceta, String instruccionesReceta) {
        this.imagenUrl = imagenUrl;
        this.nombreReceta = nombreReceta;
        this.instruccionesReceta = instruccionesReceta;
    }

    public Receta() {

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
