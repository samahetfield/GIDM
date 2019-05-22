package com.example.gidm.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity
public class Operaciones {
    @PrimaryKey(autoGenerate = true)
    public Integer oid;

    @ColumnInfo(name = "Título operación")
    public String titulo_operacion;

    @ColumnInfo(name = "Cantidad")
    public Double cantidad;


    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getTitulo_operacion() {
        return titulo_operacion;
    }

    public void setTitulo_operacion(String titulo_operacion) {
        this.titulo_operacion = titulo_operacion;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
}
