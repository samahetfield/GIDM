package com.example.gidm.db;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface Usuario_hace_operaciones_Dao {
    @Query("SELECT * FROM Usuario_hace_Operaciones")
    List<Usuario_hace_Operaciones> getAll();

    @Query("SELECT sum(uo.Cantidad) FROM Usuario_hace_Operaciones uo, Usuarios u WHERE u.uid = uo.`Dirigida a` AND u.uid=:id")
    Double getCantidad(Integer id);

    @Insert
    long insert(Usuario_hace_Operaciones usuario_hace_operaciones);

    @Delete
    void delete(Usuario_hace_Operaciones usuario_hace_operaciones);
}
