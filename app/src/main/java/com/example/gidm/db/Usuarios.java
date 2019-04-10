package com.example.gidm.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity
public class Usuarios {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "Nombre usuario")
    public String username;
}
