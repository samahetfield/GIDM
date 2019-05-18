package com.example.gidm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gidm.db.AppDatabase;
import com.example.gidm.db.Grupos;
import com.example.gidm.db.Usuario_pertenece_Grupo;
import com.example.gidm.db.Usuarios;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MainActivity.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private AppDatabase mDb;
    private VistaGrupo vistaGrupo = null;
    private Gastos gastos = null;
    Integer id_grupo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vistaGrupo = new VistaGrupo();
        gastos = new Gastos();

        mDb = AppDatabase.getDatabase(getApplicationContext());


        // Buscamos si hay algún grupo seleccionado
        if(getIntent().getExtras() != null){
            if(getIntent().getExtras().containsKey("ID_grupo")){
                id_grupo = Integer.parseInt(getIntent().getStringExtra("ID_grupo"));
            }
        }

        NavigationView navView = findViewById(R.id.nav_view);

        //Si hay algún grupo seleccionado mostramos sus usuarios por pantalla y el nombre en el Navigation Drawer

        if(id_grupo != 0){
            View headerView = navView.getHeaderView(0);
            TextView nombre_grupo_cabecera = headerView.findViewById(R.id.nombre_grupo_cabecera);

            Grupos grupo = mDb.gruposDao().getGrupo(id_grupo);
            nombre_grupo_cabecera.setText(grupo.getGroupName());

            vistaGrupo = new VistaGrupo(id_grupo);
            gastos = new Gastos(id_grupo);
        }
        mSectionsPagerAdapter = new MainActivity.SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(vistaGrupo, "Vista Grupo");
        adapter.addFragment(gastos, "Gastos");

        mViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(mViewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu m = navigationView.getMenu();
        SubMenu topChannelMenu = m.addSubMenu("Mis grupos");

        /*

            Obtenemos los grupos existentes en la base de datos para ponerlos en el Navigation Drawer

            Además en la cabecera ponemos como título el nombre del grupo que tenemos seleccionado
         */

        final List<Grupos> grupos = mDb.gruposDao().getAll();
        for(int i=0; i<grupos.size(); i++){
            topChannelMenu.add(grupos.get(i).getGroupName());
            final int finalI = i;
            topChannelMenu.getItem(i).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("ID_grupo", String.valueOf(grupos.get(finalI).gid));
                    startActivity(intent);
                    return true;
                }
            });
        }

        MenuItem mi = m.getItem(m.size()-1);
        mi.setTitle(mi.getTitle());



        /*
            En las siguientes líneas indicamos el comportamiento de los botones del Menú Flotante

         */

        FloatingActionButton fab_gasto = findViewById(R.id.accion_gasto);
        fab_gasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id_grupo == 0){
                    Toast.makeText(MainActivity.this, "Debes seleccionar un grupo para el gasto", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), NuevoGasto.class);
                    intent.putExtra("ID_Grupo", id_grupo);
                    startActivity(intent);
                }
            }
        });


        com.getbase.floatingactionbutton.FloatingActionButton fab_pago = findViewById(R.id.accion_pago);
        fab_pago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id_grupo == 0){
                    Toast.makeText(MainActivity.this, "Debes seleccionar un grupo para el pago", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), NuevoPago.class);
                    intent.putExtra("ID_Grupo", id_grupo);
                    startActivity(intent);
                }
            }
        });

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_new) {
            Intent intent = new Intent(getApplicationContext(), CrearGrupo.class);
            startActivity(intent);
        } else if (id == R.id.nav_delete) {
            if(id_grupo != 0){
                eliminarGrupo();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                id_grupo = 0;
                startActivity(intent);
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void eliminarGrupo() {
        List<Usuarios> upg = mDb.usuarios_pertenece_grupo_dao().getUsuariosGrupos(id_grupo);
        List<Usuario_pertenece_Grupo> user_group = mDb.usuarios_pertenece_grupo_dao().getUPG(id_grupo);


        for(int i=0; i<user_group.size(); i++){
            mDb.usuarios_pertenece_grupo_dao().delete(user_group.get(i));
        }

        for (int i=0; i<upg.size(); i++){
            mDb.usuariosDao().delete(upg.get(i));
        }


        Grupos grupo = mDb.gruposDao().getGrupo(id_grupo);

        mDb.gruposDao().delete(grupo);

    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static MainActivity.PlaceholderFragment newInstance(int sectionNumber) {
            MainActivity.PlaceholderFragment fragment = new MainActivity.PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main2, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Fragment fragment = null;

            switch (position){
                case 0:
                    fragment = new VistaGrupo();
                    break;
                case 1:
                    fragment = new Gastos();
                    break;
            }


            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }

}
