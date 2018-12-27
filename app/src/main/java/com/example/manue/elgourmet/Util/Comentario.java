package com.example.manue.elgourmet.Util;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "comentarios")
public class Comentario {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @NonNull
    private String nombre;
    @NonNull
    private String asunto;
    @NonNull
    private String comentario;

    public Comentario() {

    }

    public Comentario(long id, String nombre, String asunto, String comentario) {
        this.id = id;
        this.nombre = nombre;
        this.asunto = asunto;
        this.comentario = comentario;
    }

    public Comentario(String nombre, String asunto, String comentario) {
        this.nombre = nombre;
        this.asunto = asunto;
        this.comentario = comentario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
