package tablerocolores.cfic.edu.tugramola.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.LoginFilter;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tablerocolores.cfic.edu.tugramola.R;
import tablerocolores.cfic.edu.tugramola.adapter.AdapterPersona;
import tablerocolores.cfic.edu.tugramola.dao.BaseDatos;
import tablerocolores.cfic.edu.tugramola.dto.Persona;
import tablerocolores.cfic.edu.tugramola.dto.PersonaFila;

public class CreditosActivity extends AppCompatActivity {
    private BaseDatos baseDatos=null;
    private RecyclerView recView;
    private List<Persona> datos;
    private AdapterPersona adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);
        //así dibujo la flecha de navegación estandar atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

/*
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
*/

        datos = iniBaseDatos();
        List<PersonaFila> datosFila = new ArrayList<PersonaFila>(datos.size());

        for (Persona person: datos)
        {
            PersonaFila personaFila = new PersonaFila();
            personaFila.setId(person.getId());
            personaFila.setNombre(person.getNombre());
            personaFila.setLin(person.getLin());
            personaFila.setGit(person.getGit());
            personaFila.setFoto(person.getFoto());

            Log.d("MIAPP", "Creditos#0 :"+personaFila.getNombre()+" -Lin:"+personaFila.getLin());

            if (person.getLin().isEmpty()){
                personaFila.setLinIco("linko");
            } else {
                personaFila.setLinIco("linok");
            }

            if (person.getGit().isEmpty()){
                personaFila.setGitIco("gitko");
            } else {
                personaFila.setGitIco("gitok");
            }

            if (person.getFoto().isEmpty()){
                personaFila.setFoto("foto_ko");
            }

            datosFila.add(personaFila);
        }

        int elementos = datosFila.size();
        Log.d("MIAPP","Creditos#1.Tamaño="+elementos);

        if (elementos>0) {
            adaptador = new AdapterPersona(datosFila);
            recView = (RecyclerView) findViewById(R.id.myrecycview);
            //recView.setHasFixedSize(true);//opcional, si sé que el tamaño no va a cambiar

            recView.setAdapter(adaptador);//mostrando la lista

            recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recView.addItemDecoration(
                    new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        } else{
            finish();
            Toast toast = Toast.makeText(this, "Sin registros a mostrar", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    public List<Persona> iniBaseDatos() {
        //creo el objeto de la base de datos
        Log.d("MIAPP","Creditos#3");
        BaseDatos baseDatos = new BaseDatos(this, "personasBBDD", null, 1);

        List<Persona> lista=null;

        Log.d("MIAPP","Creditos#4");
        Persona p1 = new Persona(1,"Alvaro Martinez Sainz-Vizcaya","","alvaro-martinez-sainz-vizcaya-53bb7423",
                "sainzvizcaya");
        Persona p2 = new Persona(2,"Ana Esther Rodriguez Sevilla","","anaestherrs","anaesther");
        Persona p3 = new Persona(3,"Fernando Hernandez Velasco","","","fernandohv");
        Persona p4 = new Persona(4,"Ignacio Flamarique Arbizu","","","");
        Persona p5 = new Persona(5,"Henry Paul","","","HenryPaul88");
        Persona p6 = new Persona(6,"Ismael ","","","");
        Persona p7 = new Persona(7,"Javier Gonzalez","","","jgpal");
        Persona p8 = new Persona(8,"Jose Miguel NG","","","");
        Persona p9 = new Persona(9,"Juan Francisco Alonso","","","");
        Persona p10 = new Persona(10,"Julian Navamuel","","","");
        Persona p11 = new Persona(11,"Julio Cesar Calderon","","","");
        Persona p12 = new Persona(12,"Mayca Valverde","","","");
        Persona p13 = new Persona(13,"Nacho Baile","","nachobaile","nachobaile");
        Persona p14 = new Persona(14,"Pablo","","","");
        Persona p15 = new Persona(15,"Pedro Martinez Justo","","","");
        Persona p16 = new Persona(16,"Raul Martín","","","raulprogramacionfuenla");
        Persona p17 = new Persona(17,"S.Carolina Jiménez Pinzón","foto_carolina",
                "stephany-carolina-jiménez-pinzón-32b0448b","");

        if (!baseDatos.existe()) {
            Log.d("MIAPP", "Creditos#5");
            baseDatos.insertPersona(p1);
            baseDatos.insertPersona(p2);
            baseDatos.insertPersona(p3);
            baseDatos.insertPersona(p4);
            baseDatos.insertPersona(p5);
            baseDatos.insertPersona(p6);
            baseDatos.insertPersona(p7);
            baseDatos.insertPersona(p8);
            baseDatos.insertPersona(p9);
            baseDatos.insertPersona(p10);
            baseDatos.insertPersona(p11);
            baseDatos.insertPersona(p12);
            baseDatos.insertPersona(p13);
            baseDatos.insertPersona(p14);
            baseDatos.insertPersona(p15);
            baseDatos.insertPersona(p16);
            baseDatos.insertPersona(p17);
        }

        Log.d("MIAPP","Creditos#6");
        lista = baseDatos.cargarPersonas();

        Log.d("MIAPP", "Creditos#7.LISTA:");
        for (Persona pers: lista)
        {
            Log.d("MIAPP", pers.getNombre()+"#"+pers.getLin());
        }

        return lista;

    }

    //Recibimos evento para la flecha estandar

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                Log.d("MIAPP", "Creditos#2.Atras");
                super.onBackPressed();
                break;
        }
        return true;
    }


}
