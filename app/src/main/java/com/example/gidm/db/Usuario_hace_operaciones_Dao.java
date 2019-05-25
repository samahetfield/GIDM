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

    @Query("SELECT uo.*, o.* FROM Usuario_hace_Operaciones uo, Operaciones o, Usuarios u, Usuario_pertenece_grupo ug INNER JOIN Operaciones ON o.oid = uo.`ID Operación` WHERE ug.`ID grupo` = :id_grupo AND ug.`ID usuario` = u.uid AND uo.`ID usuario` = ug.`ID usuario` GROUP BY `ID Operación`")
    List<Operaciones_Gastos> getOperaciones(Integer id_grupo);

    @Query("SELECT * FROM Usuario_hace_Operaciones uo, Usuario_pertenece_Grupo ug WHERE ug.`ID grupo` = :id_grupo AND uo.`ID usuario` = ug.`ID usuario`")
    List<Usuario_hace_Operaciones> getoperacionesGrupo(Integer id_grupo);

    @Query("SELECT * FROM Usuario_hace_Operaciones WHERE `ID usuario` = :user")
    Usuario_hace_Operaciones getOperacionUsuario(Integer user);


    @Insert
    long insert(Usuario_hace_Operaciones usuario_hace_operaciones);

    @Delete
    void delete(Usuario_hace_Operaciones usuario_hace_operaciones);
}
