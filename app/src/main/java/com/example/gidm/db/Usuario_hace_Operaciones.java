package com.example.gidm.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;


@Entity(foreignKeys = {
        @ForeignKey(entity = Operaciones.class,
                    parentColumns = "oid",
                    childColumns = "ID Operación"),
        @ForeignKey(entity = Usuarios.class,
                    parentColumns = "uid",
                    childColumns = "ID usuario"),
        @ForeignKey(entity = Usuarios.class,
                    parentColumns = "uid",
                    childColumns = "Dirigida a")
})
public class Usuario_hace_Operaciones {
    @PrimaryKey
    public Integer uoid;

    @ColumnInfo(name = "ID Operación")
    public Integer oper_id;

    @ColumnInfo(name = "ID usuario")
    public Integer user_id;

    @ColumnInfo(name = "Dirigida a")
    public Integer user_receive;

}
