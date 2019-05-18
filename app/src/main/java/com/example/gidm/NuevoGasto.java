package com.example.gidm;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.gidm.db.AppDatabase;
import com.example.gidm.db.Usuario_pertenece_Grupo;
import com.example.gidm.db.Usuarios;

import java.util.ArrayList;
import java.util.List;

public class NuevoGasto extends AppCompatActivity {
    Integer id_grupo = 0;
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_gasto);
        mDb = AppDatabase.getDatabase(getApplicationContext());


        id_grupo = getIntent().getExtras().getInt("ID_Grupo");

        final List<String> users = mDb.usuarios_pertenece_grupo_dao().getNombresUsuariosGrupos(id_grupo);
        final CharSequence[] cs = users.toArray(new CharSequence[users.size()]);


        final TextView quien_paga = findViewById(R.id.de_persona);

        quien_paga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(NuevoGasto.this);
                mBuilder.setTitle("Elige quién ha comprado");

                mBuilder.setSingleChoiceItems(cs, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        quien_paga.setText(cs[which]);
                        dialog.dismiss();
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });


        final TextView quien_recibe = findViewById(R.id.para_personas);
        int seleccionados = 0;
        quien_recibe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<String> usuarios_seleccionados = new ArrayList();
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(NuevoGasto.this);
                mBuilder.setTitle("Elige para quién es la compra");
                mBuilder.setMultiChoiceItems(cs, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked){
                            usuarios_seleccionados.add((String) cs[which]);
                        }
                        else{
                            usuarios_seleccionados.remove((String) cs[which]);
                        }
                    }
                });
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        quien_recibe.setText(String.valueOf(usuarios_seleccionados.size()) + " personas");
                        dialog.dismiss();
                    }
                });


                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

    }
}
