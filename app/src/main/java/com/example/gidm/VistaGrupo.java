package com.example.gidm;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.gidm.db.AppDatabase;
import com.example.gidm.db.Usuarios;

import java.util.List;

public class VistaGrupo extends Fragment {
    View view;
    Integer id_grupo;
    private AppDatabase mDb;

    public VistaGrupo(){
        id_grupo = 0;
    }

    @SuppressLint("ValidFragment")
    public VistaGrupo(Integer idGrupo){
        id_grupo = idGrupo;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_vistagrupo, container, false);

        mDb = AppDatabase.getDatabase(view.getContext());

        List<Usuarios> usuarios_grupo = mDb.usuarios_pertenece_grupo_dao().getUsuariosGrupos(id_grupo);


        TableLayout table = view.findViewById(R.id.tabla_usuarios);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 5f);
        layoutParams.setMargins(0,10,0,10);
        for(int i=0; i<usuarios_grupo.size(); i++){
            Log.d("Usuario", usuarios_grupo.get(i).getUsername());
            TableRow row = new TableRow(view.getContext());

            Usuarios usuario1 = usuarios_grupo.get(i);

            Double cantidad = mDb.usuario_hace_operaciones_dao().getCantidad(usuario1.getUid());

            TextView nombre_usuario = new TextView(view.getContext());
            nombre_usuario.setTextSize(18);
            nombre_usuario.setLayoutParams(layoutParams);

            nombre_usuario.setText(usuarios_grupo.get(i).getUsername());

            TextView balance = new TextView(view.getContext());
            balance.setText(String.valueOf(cantidad));
            balance.setLayoutParams(layoutParams);
            balance.setTextSize(18);

            row.addView(nombre_usuario);
            row.addView(balance);
            table.addView(row);
        }


        return view;
    }
}
