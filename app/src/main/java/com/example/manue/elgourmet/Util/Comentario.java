package com.example.manue.elgourmet.Util;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "comentarios")
public class Comentario{

    @PrimaryKey(autoGenerate = true)
    private long id;
    @NonNull
    private String comentario;

    public Comentario() {

    }

    public Comentario(long id,String comentario) {
        this.id = id;
        this.comentario = comentario;
    }

    public Comentario( String comentario) {
        this.comentario = comentario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}
