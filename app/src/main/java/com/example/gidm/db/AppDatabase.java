package com.example.gidm.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Grupos.class, Operaciones.class, Usuarios.class, Usuario_hace_Operaciones.class, Usuario_pertenece_Grupo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract GruposDao gruposDao();
    public abstract UsuariosDao usuariosDao();
    public abstract Usuarios_pertenece_Grupo_Dao usuarios_pertenece_grupo_dao();
    public abstract OperacionesDao operacionesDao();
    public abstract Usuario_hace_operaciones_Dao usuario_hace_operaciones_dao();


    public static AppDatabase getDatabase(Context context){

        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "GIDM2").allowMainThreadQueries().build();
        }

        return INSTANCE;
}

    public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                            // To simplify the codelab, allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
