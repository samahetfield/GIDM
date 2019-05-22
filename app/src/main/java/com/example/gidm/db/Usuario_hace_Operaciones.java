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
    @PrimaryKey(autoGenerate = true)
    public Integer uoid;

    @ColumnInfo(name = "ID Operación")
    public Integer oper_id;

    @ColumnInfo(name = "ID usuario")
    public Integer user_id;

    @ColumnInfo(name = "Dirigida a")
    public Integer user_receive;

    @ColumnInfo(name = "Cantidad")
    public Double cantidad;

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getUoid() {
        return uoid;
    }

    public void setUoid(Integer uoid) {
        this.uoid = uoid;
    }

    public Integer getOper_id() {
        return oper_id;
    }

    public void setOper_id(Integer oper_id) {
        this.oper_id = oper_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getUser_receive() {
        return user_receive;
    }

    public void setUser_receive(Integer user_receive) {
        this.user_receive = user_receive;
    }
}
