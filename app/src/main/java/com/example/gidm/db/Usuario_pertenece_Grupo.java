package com.example.gidm.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;


@Entity(foreignKeys = {@ForeignKey(entity = Usuarios.class,
                                    parentColumns = "uid",
                                    childColumns = "user_id"),
                        @ForeignKey(entity = Grupos.class,
                                    parentColumns = "gid",
                                    childColumns = "group_id")
})

public class Usuario_pertenece_Grupo {
    @PrimaryKey
    public int pid;

    @ColumnInfo(name = "ID grupo")
    public int group_id;

    @ColumnInfo(name = "ID usuario")
    public int user_id;

}
