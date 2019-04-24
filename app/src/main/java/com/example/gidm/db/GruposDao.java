package com.example.gidm.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface GruposDao {
    @Query("SELECT * FROM Grupos")
    List<Grupos> getAll();

    @Query("SELECT * FROM Grupos WHERE gid = :grupoID")
    Grupos getGrupo(int grupoID);

    @Insert
    long insert(Grupos grupo);

    @Delete
    void delete(Grupos grupo);

}
