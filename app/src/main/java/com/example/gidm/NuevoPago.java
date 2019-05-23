package com.example.gidm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gidm.db.AppDatabase;
import com.example.gidm.db.Usuario_hace_Operaciones;

import org.w3c.dom.Text;

import java.util.List;

public class NuevoPago extends AppCompatActivity {

    Integer id_grupo;
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_pago);

        id_grupo = (Integer) getIntent().getExtras().get("ID_grupo");

        mDb = AppDatabase.getDatabase(getApplicationContext());

        List<Usuario_hace_Operaciones> operaciones = mDb.usuario_hace_operaciones_dao().getoperacionesGrupo(id_grupo);

        LinearLayout pagos_parent = findViewById(R.id.pagos);

        for (int i=0; i<operaciones.size(); i++){
            if(operaciones.get(i).getUser_id() != operaciones.get(i).getUser_receive()){
                TextView tv1 = new TextView(this);
                String username = mDb.usuariosDao().getUsername(operaciones.get(i).getUser_receive());
                String username2 = mDb.usuariosDao().getUsername(operaciones.get(i).getUser_id());
                tv1.setText(username + "Debe dinero a " + username2);

                TextView tv2 = new TextView(this);
                tv2.setText(operaciones.get(i).getCantidad().toString());


                pagos_parent.addView(tv1);
                pagos_parent.addView(tv2);
            }


        }
        
    }
}
