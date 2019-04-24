package com.example.gidm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Gastos extends Fragment {
    View view;
    Integer id_grupo;

    public Gastos(){
        id_grupo = 0;
    }

    public Gastos(Integer idGrupo){
        id_grupo = idGrupo;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gastos, container, false);
        return view;
    }
}
