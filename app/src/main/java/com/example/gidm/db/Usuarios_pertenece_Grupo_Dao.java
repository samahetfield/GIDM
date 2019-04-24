package com.example.gidm.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface Usuarios_pertenece_Grupo_Dao {

    @Query("SELECT * FROM Usuario_pertenece_Grupo")
    List<Usuario_pertenece_Grupo> getAll();

    @Query("SELECT * FROM Usuario_pertenece_Grupo upg, Usuarios u WHERE upg.'ID grupo' = :id_grupo AND u.uid= upg.`ID usuario`")
    List<Usuarios> getUsuariosGrupos(Integer id_grupo);

    @Insert
    long insert(Usuario_pertenece_Grupo usuario_pertenece_grupo);

    @Delete
    void delete(Usuario_pertenece_Grupo usuario_pertenece_grupo);

}
