package com.example.manue.elgourmet.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.manue.elgourmet.Util.Comentario;

import java.util.List;

@Dao
public interface ComentarioDAO {

    @Insert
    public long insertComentario(Comentario comentario);

    @Query("SELECT * FROM comentarios")
    public List<Comentario> getComentarios();
}
