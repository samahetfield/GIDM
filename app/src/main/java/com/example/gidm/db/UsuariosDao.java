package com.example.gidm.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UsuariosDao {
    @Query("SELECT * FROM Usuarios")
    List<Usuarios> getAll();

    @Insert
    long insert(Usuarios usuarios);

    @Delete
    void delete(Usuarios usuarios);

}
