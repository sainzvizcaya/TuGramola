package tablerocolores.cfic.edu.tugramola.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.NavigationView;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import tablerocolores.cfic.edu.tugramola.R;
import tablerocolores.cfic.edu.tugramola.dao.BaseDatosCanciones;
import tablerocolores.cfic.edu.tugramola.fragments.BuscarFragment;
import tablerocolores.cfic.edu.tugramola.fragments.Fragment_favoritos;
import tablerocolores.cfic.edu.tugramola.fragments.Fragment_main;
import tablerocolores.cfic.edu.tugramola.fragments.ResultadosFragment;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawerLayout;
    private boolean menu_visible;//para gestionar si está visible o no el menú lateral
    public static String Url = "";

    public void buscar(View view)
    {
        Fragment fragment = new BuscarFragment();
        //Toast.makeText(this,"buscar",Toast.LENGTH_SHORT).show();
        Log.d("MIAPP","Pestaña buscar");
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedor,fragment);
        fragmentTransaction.commit();
        // subrayamos y cambiamos de color el texto actual y hacemos lo contrario en los otros dos
        TextView text = findViewById(R.id.t_buscar);
        text.setTextColor(Color.CYAN);
        //text.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        text.setText(Html.fromHtml("<u>BUSCAR</u>"));
        // cambiamos los otros
        TextView text2 = findViewById(R.id.t_resultados);
        text2.setText("RESULTADOS");
        text2.setTextColor(Color.WHITE);
        TextView text3 = findViewById(R.id.t_favoritos);
        text3.setText("FAVORITOS");
        //text3.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        text3.setTextColor(Color.WHITE);

    }

    public void resultados(View view)
    {
        //Toast.makeText(this,"resultados",Toast.LENGTH_SHORT).show();
        Log.d("MIAPP","Pestaña resultados");
        Fragment fragment = new Fragment_main();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedor,fragment);
        fragmentTransaction.commit();
        // subrayamos y cambiamos de color el texto actual y hacemos lo contrario en los otros dos
        TextView text = findViewById(R.id.t_resultados);
        //text.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        text.setTextColor(Color.CYAN);
        text.setText(Html.fromHtml("<u>RESULTADOS</u>"));
        // cambiamos los otros
        TextView text2 = findViewById(R.id.t_buscar);
        text2.setText("BUSCAR");
        text2.setTextColor(Color.WHITE);
        TextView text3 = findViewById(R.id.t_favoritos);
        text3.setText("FAVORITOS");
        text3.setTextColor(Color.WHITE);

    }

    public void favoritos(View view)
    {
        //Toast.makeText(this,"favoritos",Toast.LENGTH_SHORT).show();
        Log.d("MIAPP","Pestaña favoritos");
        Fragment fragment = new Fragment_favoritos();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedor,fragment);
        fragmentTransaction.commit();
        // subrayamos y cambiamos de color el texto actual y hacemos lo contrario en los otros dos
        TextView text = findViewById(R.id.t_favoritos);
        //text.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        text.setTextColor(Color.CYAN);
        text.setText(Html.fromHtml("<u>FAVORITOS</u>"));
        // cambiamos los otros
        TextView text2 = findViewById(R.id.t_buscar);
        text2.setText("BUSCAR");
        text2.setTextColor(Color.WHITE);
        TextView text3 = findViewById(R.id.t_resultados);
        text3.setText("RESULTADOS");
        text3.setTextColor(Color.WHITE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.menu_visible = false;



        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navview);
        navigationView.setNavigationItemSelectedListener(this); //escucho los eventos de esta clase aquí
        //este es el que cambia el color de fondo
        navigationView.setBackgroundColor(getResources().getColor(R.color.colorGrisPlomo));
        // mostramos la pestaña de busqueda al arrancar la aplicacion
        TextView t = findViewById(R.id.t_buscar);
        buscar(t);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id_item = item.getItemId();
        switch (id_item) {


            case android.R.id.home:
                if (menu_visible) {
                    drawerLayout.closeDrawers();
                    menu_visible = false;
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                    menu_visible = true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Provoca la finalización de la aplicación
     */
    public void salirAplicacion2() {
        //si el terminal es versión superior API 15
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity(); //Cierra la app completamente. Esta función funciona a partir del API 16
        } else {
            //delegamos en la clase padre
            super.onBackPressed(); //finish();
        }
    }

    public void salirAplicacion()
    {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("A T E N C I O N");
        dialogo1.setMessage("¿ Desea realmente salir de la aplicación ?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

                salirAplicacion2();
            }
        });
        dialogo1.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {


            }
        });
        dialogo1.show();
    }
    @Override
    public void onBackPressed() {

        salirAplicacion();
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        switch (menuItem.getItemId()) {
            //Opción del menú de Creditos
            case R.id.menu_seccion_1:
                Log.i("NavigationView", "Pulsada opción 1");
                Intent i1 = new Intent(this,CreditosActivity.class);
                startActivity(i1);
                menu_visible = false;
                break;

            //Opción del menú de Acerca de
            case R.id.menu_seccion_2:
                Log.i("NavigationView", "Pulsada opción 2");
                Intent i2 = new Intent(this,Acercade.class);
                startActivity(i2);
                menu_visible = false;
                break;

            //Opción del menú de Ajustes
            case R.id.menu_seccion_3:
                Log.i("NavigationView", "Pulsada opción 3");
                Intent i3= new Intent(this,Ajustes.class);
                startActivity(i3);
                menu_visible = false;
                break;

            //Opción del menú de Salir
            case R.id.menu_seccion_4:
                Log.i("NavigationView", "Pulsada opción 4");

                salirAplicacion();

                break;
            case R.id.borrar_favoritos:
                Log.i("NavigationView", "Pulsada opción 4");
                borrar_favoritos();
                menu_visible = false;
        }

        drawerLayout.closeDrawers();

        return true;
    }
    // borramos los favoritos de la bbdd
    public void borrar_favoritos()
    {
        AlertDialog.Builder dialogo2 = new AlertDialog.Builder(this);
        dialogo2.setTitle("A T E N C I O N");
        dialogo2.setMessage("Esto eliminara los favoritos guardados. ¿Desea continuar?");
        dialogo2.setCancelable(false);
        dialogo2.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BaseDatosCanciones baseDatosCanciones = new BaseDatosCanciones(getApplicationContext(),"ITUNESBD",null,1);
                baseDatosCanciones.eliminarTodos();
            }
        });
        dialogo2.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // no hariamos nada
            }
        });
        dialogo2.show();
    }

}