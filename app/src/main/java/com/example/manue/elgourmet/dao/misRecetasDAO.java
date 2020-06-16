package com.example.manue.elgourmet.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.manue.elgourmet.Util.MiReceta;

import java.util.List;

@Dao
public interface misRecetasDAO {

    @Insert
    public long insertReceta(MiReceta receta);

    @Delete
    public int deleteReceta(MiReceta receta);

    @Query("SELECT * FROM misRecetas")
    public List<MiReceta> getRecetas();
}
