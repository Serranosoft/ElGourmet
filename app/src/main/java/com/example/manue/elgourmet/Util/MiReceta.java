package com.example.manue.elgourmet.Util;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "misRecetas")
public class MiReceta {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    private String imagenUrl;
    @NonNull
    private String nombreReceta;
    @NonNull
    private String instruccionesReceta;
    @NonNull
    private String ingredientes;


    public MiReceta() {
    }

    public MiReceta(long id, String imagenUrl, String nombreReceta, String instruccionesReceta, String ingredientes, String url) {
        this.id = id;
        this.imagenUrl = imagenUrl;
        this.nombreReceta = nombreReceta;
        this.instruccionesReceta = instruccionesReceta;
        this.ingredientes = ingredientes;
    }

    public MiReceta(String imagenUrl, String nombreReceta, String ingredientes, String instruccionesReceta) {
        this.imagenUrl = imagenUrl;
        this.nombreReceta = nombreReceta;
        this.instruccionesReceta = instruccionesReceta;
        this.ingredientes = ingredientes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

}
