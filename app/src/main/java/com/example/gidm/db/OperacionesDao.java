package com.example.gidm.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface OperacionesDao {
    @Query("SELECT * FROM Operaciones")
    List<Operaciones> getAll();

    @Insert
    long insert(Operaciones operaciones);

    @Delete
    void delete(Operaciones operaciones);

}

