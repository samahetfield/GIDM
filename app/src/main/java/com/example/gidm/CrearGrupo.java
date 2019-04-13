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

public class CrearGrupo extends AppCompatActivity {
    ListView lista_grupo;
    ArrayAdapter<String> adapter;
    EditText nombre_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_grupo);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
