package com.example.gidm;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gidm.db.AppDatabase;
import com.example.gidm.db.Operaciones;
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

        final List<Usuario_hace_Operaciones> operaciones = mDb.usuario_hace_operaciones_dao().getoperacionesGrupo(id_grupo);

        LinearLayout pagos_parent = findViewById(R.id.pagos);

        for (int i=0; i<operaciones.size(); i++){
            ViewGroup.LayoutParams lyp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ((LinearLayout.LayoutParams) lyp).setMargins(8,8,8,8);

            LinearLayout ly1 = new LinearLayout(this);
            ly1.setOrientation(LinearLayout.VERTICAL);
            ly1.setLayoutParams(lyp);
            ly1.setBackgroundColor(Color.WHITE);

            if(operaciones.get(i).getUser_id() != operaciones.get(i).getUser_receive()){
                TextView tv1 = new TextView(this);
                final String username = mDb.usuariosDao().getUsername(operaciones.get(i).getUser_receive());
                final String username2 = mDb.usuariosDao().getUsername(operaciones.get(i).getUser_id());

                tv1.setLayoutParams(lyp);
                tv1.setTextSize(18);
                tv1.setText(username + " Debe dinero a " + username2);

                TextView tv2 = new TextView(this);
                tv2.setLayoutParams(lyp);
                tv2 .setTextSize(18);
                tv2.setText(operaciones.get(i).getCantidad().toString());

                ly1.addView(tv1);
                ly1.addView(tv2);

                pagos_parent.addView(ly1);


                final int finalI = i;
                ly1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(NuevoPago.this);
                        mBuilder.setTitle("¿Desea realizar el pago?");

                        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mDb.usuario_hace_operaciones_dao().delete(operaciones.get(finalI));

                               /*
                                Operaciones operacion_pago= new Operaciones();
                                operacion_pago.setCantidad(operaciones.get(finalI).getCantidad());
                                operacion_pago.setTitulo_operacion("Pago de " + username + " hacia " + username2);

                                long id_oper = mDb.operacionesDao().insert(operacion_pago);
                                operacion_pago.setOid((int) id_oper);

                                Usuario_hace_Operaciones pago_hecho = new Usuario_hace_Operaciones();
                                pago_hecho.setOper_id((int) id_oper);
                                pago_hecho.setCantidad(-operaciones.get(finalI).getCantidad());
                                pago_hecho.setUser_id(mDb.usuariosDao().getID(username2));
                                pago_hecho.setUser_receive(mDb.usuariosDao().getID(username));

                                long id_pago = mDb.usuario_hace_operaciones_dao().insert(pago_hecho);
                                pago_hecho.setUoid((int) id_pago);
*/
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("ID_grupo", String.valueOf(id_grupo));
                                startActivity(intent);

                            }
                        });
                        mBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        AlertDialog mDialog = mBuilder.create();
                        mDialog.show();
                    }
                });

            }




        }
        
    }
}
