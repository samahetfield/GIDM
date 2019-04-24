package com.example.gidm.db;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseClient {
    private Context mCtx;
    private static DatabaseClient mInstance;
    //our app database object
    private AppDatabase appDatabase;

    private DatabaseClient(Context ctx){
        this.mCtx = ctx;

        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "GIDM").build();
    }

    public static synchronized DatabaseClient getInstance(Context ctx){
        if(mInstance == null)
            mInstance = new DatabaseClient(ctx);

        return mInstance;
    }

    public AppDatabase getAppDatabase(){
        return appDatabase;
    }
}
