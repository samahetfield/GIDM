package com.example.gidm;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gidm.db.AppDatabase;
import com.example.gidm.db.Operaciones;
import com.example.gidm.db.Operaciones_Gastos;

import java.util.List;

public class Gastos extends Fragment {
    View view;
    Integer id_grupo;
    private AppDatabase mDb;

    public Gastos(){
        id_grupo = 0;
    }

    @SuppressLint("ValidFragment")
    public Gastos(Integer idGrupo){
        id_grupo = idGrupo;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gastos, container, false);

        mDb = AppDatabase.getDatabase(view.getContext());

        List<Operaciones_Gastos> operaciones = mDb.usuario_hace_operaciones_dao().getOperaciones(id_grupo);


        LinearLayout gastos_parent = view.findViewById(R.id.gastos_fragment);

        for (int i=0; i<operaciones.size(); i++){

            ViewGroup.LayoutParams lyp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ((LinearLayout.LayoutParams) lyp).setMargins(8,8,8,8);

            LinearLayout nuevo_gasto = new LinearLayout(view.getContext());
            nuevo_gasto.setOrientation(LinearLayout.VERTICAL);
            nuevo_gasto.setBackgroundColor(Color.parseColor("#ffffff"));
            nuevo_gasto.setLayoutParams(lyp);

            TextView tv = new TextView(view.getContext());
            tv.setLayoutParams(lyp);
            tv.setText(operaciones.get(i).getOperaciones().getTitulo_operacion());
            tv.setTextSize(24);

            TextView tv2 = new TextView(view.getContext());
            tv2.setLayoutParams(lyp);
            tv2.setText("Cantidad: " + operaciones.get(i).getOperaciones().getCantidad().toString());
            tv2.setTextSize(24);

            TextView tv3 = new TextView(view.getContext());
            tv3.setLayoutParams(lyp);
            String username = mDb.usuariosDao().getUsername(operaciones.get(i).getUsuario_hace_operaciones().getUser_id());
            tv3.setText("Gasto hecho por " + username);

            nuevo_gasto.addView(tv);
            nuevo_gasto.addView(tv2);
            nuevo_gasto.addView(tv3);

            gastos_parent.addView(nuevo_gasto);

        }


        return view;
    }
}
