package com.example.gidm;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gidm.db.AppDatabase;
import com.example.gidm.db.DatabaseClient;
import com.example.gidm.db.Grupos;
import com.example.gidm.db.Usuario_pertenece_Grupo;
import com.example.gidm.db.Usuarios;

public class CrearGrupo extends AppCompatActivity {
    ListView lista_grupo;
    ArrayAdapter<String> adapter;
    EditText nombre_edit;
    EditText nombre_grupo;
    private AppDatabase mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_grupo);
        mDb = AppDatabase.getDatabase(getApplicationContext());


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nombre_grupo = findViewById(R.id.group_name);
        nombre_edit = findViewById(R.id.nombre_persona);

        nombre_edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    String nombre = String.valueOf(nombre_edit.getText());

                    adapter.add(nombre);
                    adapter.notifyDataSetChanged();
                    nombre_edit.getText().clear();
                    handled = true;
                }

                return handled;
            }
        });


        lista_grupo = findViewById(R.id.lista_grupo);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        lista_grupo.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.crear_grupo, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.action_crear:

                String nombre_de_grupo = nombre_grupo.getText().toString();

                Grupos grupo = new Grupos(nombre_de_grupo);

                long grupoId = mDb.gruposDao().insert(grupo);
                grupo.setGid((int) grupoId);

                for(int i=0; i<adapter.getCount(); i++){
                    String nombre_user = adapter.getItem(i);

                    Usuarios usuario = new Usuarios(nombre_user);

                    long usuarioid = mDb.usuariosDao().insert(usuario);
                    usuario.setUid((int) usuarioid);

                    Usuario_pertenece_Grupo usuario_pertenece_grupo = new Usuario_pertenece_Grupo();
                    usuario_pertenece_grupo.setUser_id(usuario.getUid());
                    usuario_pertenece_grupo.setGroup_id(grupo.getGid());

                    long user_grupo = mDb.usuarios_pertenece_grupo_dao().insert(usuario_pertenece_grupo);
                    usuario_pertenece_grupo.setPid((int) user_grupo);

                }


                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("ID_Grupo", grupoId);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
