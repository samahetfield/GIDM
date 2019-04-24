package com.example.gidm.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity
public class Grupos {
    @PrimaryKey(autoGenerate = true)
    public Integer gid;

    @ColumnInfo(name = "Nombre grupo")
    @NonNull
    public String groupName;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Grupos(@NonNull String groupName){
        this.groupName = groupName;
    }
}
