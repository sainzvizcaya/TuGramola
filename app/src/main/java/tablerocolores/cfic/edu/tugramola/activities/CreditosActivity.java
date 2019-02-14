package tablerocolores.cfic.edu.tugramola.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;


import tablerocolores.cfic.edu.tugramola.R;
import tablerocolores.cfic.edu.tugramola.adapter.AdapterPersona;
import tablerocolores.cfic.edu.tugramola.dao.BaseDatos;
import tablerocolores.cfic.edu.tugramola.dto.Persona;

public class CreditosActivity extends AppCompatActivity {
    private RecyclerView recView;
    private List<Persona> datos;
    private AdapterPersona adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //datos = Preferencias.cargarPuntuaciones(this);

        datos = iniBaseDatos();


        int elementos = datos.size();
        if (elementos>0) {
            adaptador = new AdapterPersona(datos);
            recView = (RecyclerView) findViewById(R.id.myrecycview);
            //recView.setHasFixedSize(true);//opcional, si sé que el tamaño no va a cambiar


            recView.setAdapter(adaptador);//mostrando la lista

            recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            //recView.setLayoutManager(new GridLayoutManager(this,3));
            //StaggeredGridLayoutManager para celdas de tamaño variable
            //recView.setLayoutManager(new StaggeredGridLayoutManager());


            //ITEM DECORATOR --> OPCIONAL

            recView.addItemDecoration(
                    new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

            //
            //recView.setItemAnimator(new DefaultItemAnimator());

            //registerForContextMenu(recView);

            // recView.setContextClickable(true);
        } else{
            finish();
            //super.onBackPressed(); tb funciona.
            Toast toast = Toast.makeText(this, "No hay puntuaciones", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    //Recibimos evento para la flecha estandar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                Log.d("MIAPP", "Atras desde puntuaciones");
                super.onBackPressed();
                break;
        }
        return true;
    }


    public List<Persona> iniBaseDatos() {
        //creo el objeto de la base de datos
        BaseDatos baseDatos = new BaseDatos(this, "personasDB", null, 1);

        /*
        https://github.com/...

         */
        Persona p1 = new Persona(1,"Henry","","HenrytPaul88","");
        Persona p2 = new Persona(2, "Nombre2", "fotoPrueba2","anaesther","anaestherrs");

        baseDatos.insertPersona(p1);
        baseDatos.insertPersona(p2);

        Log.d("MIAPP", "LISTA:");
        for (Persona persona: datos)
        {
            Log.d("MIAPP", persona.getId()+"-"+persona.getNombre());
        }

        List<Persona> lista = baseDatos.cargarPersonas();
        return lista;

    }


}
