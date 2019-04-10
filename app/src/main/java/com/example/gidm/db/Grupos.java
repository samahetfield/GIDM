package com.example.gidm.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Grupos {
    @PrimaryKey
    public int gid;

    @ColumnInfo(name = "Nombre grupo")
    public String groupName;

}
