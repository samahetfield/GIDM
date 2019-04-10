package com.example.gidm.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;


@Entity(foreignKeys = {
        @ForeignKey(entity = Operaciones.class,
                    parentColumns = "oid",
                    childColumns = "oper_id"),
        @ForeignKey(entity = Usuarios.class,
                    parentColumns = "uid",
                    childColumns = "user_id"),
        @ForeignKey(entity = Usuarios.class,
                    parentColumns = "uid",
                    childColumns = "user_receive")
})
public class Usuario_hace_Operaciones {
    @PrimaryKey
    public int uoid;

    @ColumnInfo(name = "ID Operación")
    public int oper_id;

    @ColumnInfo(name = "ID usuario")
    public int user_id;

    @ColumnInfo(name = "Dirigida a ")
    public int user_receive;

}
