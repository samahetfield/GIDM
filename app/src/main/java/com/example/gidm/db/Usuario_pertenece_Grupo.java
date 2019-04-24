package com.example.gidm.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;


@Entity(foreignKeys = {@ForeignKey(entity = Usuarios.class,
                                    parentColumns = "uid",
                                    childColumns = "ID usuario"),
                        @ForeignKey(entity = Grupos.class,
                                    parentColumns = "gid",
                                    childColumns = "ID grupo")
})

public class Usuario_pertenece_Grupo {
    @PrimaryKey(autoGenerate = true)
    public Integer pid;

    @ColumnInfo(name = "ID grupo")
    public Integer group_id;

    @ColumnInfo(name = "ID usuario")
    public Integer user_id;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
