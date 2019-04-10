package com.example.gidm.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity
public class Operaciones {
    @PrimaryKey
    public int oid;

    @ColumnInfo(name = "Título operación")
    public String titulo_operacion;

    @ColumnInfo(name = "Cantidad")
    public float cantidad;

}
