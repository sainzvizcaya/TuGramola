package tablerocolores.cfic.edu.tugramola.fragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import tablerocolores.cfic.edu.tugramola.R;
import tablerocolores.cfic.edu.tugramola.dto.Cancion;
import tablerocolores.cfic.edu.tugramola.miscelaneo.ResultadoCanciones;
import tablerocolores.cfic.edu.tugramola.util.CriteriosBusqueda;


public class Busqueda extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private final String[] seleccion = {"Todas","Cancion","Artista","Album"};
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.CmbOpciones);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, seleccion);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.position=position;
        Toast.makeText(this,"Seleccionado: "+ seleccion[position],Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //TODO
    }

    public void buscar(View v){
        Log.d("LLLL","Seleccionado: "+ seleccion[position]);
        CriteriosBusqueda criterios=new CriteriosBusqueda(this);
        switch (seleccion[position]){
            case "Todas":
                criterios.todas();
                break;
            case "Cancion":
                criterios.porCancion();
                break;
            case "Artista":
                criterios.porArtista();
                break;
            case "Album":
                criterios.porAlbum();
                break;
        }
    }

    public void mostrarResultados(ResultadoCanciones rc){
        Toast.makeText(this,"FIN DESCARGA",Toast.LENGTH_LONG).show();
        for(Cancion c:rc.getResults()){
            Log.d("LLLL","Artista: "+c.getArtistName()+"   Album: "+c.getCollectionName());
        }
    }
}


