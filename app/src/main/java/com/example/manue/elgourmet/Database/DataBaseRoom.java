package com.example.manue.elgourmet.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.manue.elgourmet.Util.Comentario;
import com.example.manue.elgourmet.Util.MiReceta;
import com.example.manue.elgourmet.Util.Receta;
import com.example.manue.elgourmet.dao.ComentarioDAO;
import com.example.manue.elgourmet.dao.misRecetasDAO;

@Database(entities = {Comentario.class, MiReceta.class}, version=2, exportSchema = false)
public abstract class DataBaseRoom extends RoomDatabase {

    public abstract ComentarioDAO comentarioDAO();
    public abstract misRecetasDAO misRecetasDAO();

    private static DataBaseRoom INSTANCE = null;

    public static DataBaseRoom getInstance(final Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DataBaseRoom.class, "elgourmet.db").fallbackToDestructiveMigration().build();


        }
        return INSTANCE;
    }
    public static void destroyInstance(){
        INSTANCE=null;
    }


}
