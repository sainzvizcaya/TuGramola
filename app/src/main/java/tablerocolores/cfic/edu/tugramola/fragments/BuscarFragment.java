package tablerocolores.cfic.edu.tugramola.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import tablerocolores.cfic.edu.tugramola.R;
import tablerocolores.cfic.edu.tugramola.dto.Cancion;
import tablerocolores.cfic.edu.tugramola.dto.ResultadoCanciones;
import tablerocolores.cfic.edu.tugramola.util.CriteriosBusqueda;


/**
 * A simple {@link Fragment} subclass.
 */
public class BuscarFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    View vista;
    EditText editText;
    private final String[] seleccion = {"Todas","Canción","Artista","Album"};
    private int position;

    public BuscarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        editText = (EditText)view.findViewById(R.id.dato);

        Spinner spinner = view.findViewById(R.id.CmbOpciones);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.lista_canciones, seleccion);
        spinner.setAdapter(adapter);
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        vista = inflater.inflate(R.layout.fragment_buscar, container, false);



        return vista;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.position=position;
        Toast.makeText(getActivity().getApplicationContext(),"Seleccionado: "+ seleccion[position],Toast.LENGTH_LONG).show();
        editText.setHint("Escribe "+seleccion[position]+"...");

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //TODO
    }

    public void buscar(View v){
        Log.d("LLLL","Seleccionado: "+ seleccion[position]);
        CriteriosBusqueda criterios=new CriteriosBusqueda(this);
        switch (seleccion[position]){
            case "todas":
                Intent intent=new Intent(this.getActivity(), Fragment_main.class);
                intent.putExtra("URL", criterios.todas());
                startActivity(intent);
                break;
            case "cancion":
                Intent intent1=new Intent(this.getActivity(), Fragment_main.class);
                intent1.putExtra("URL", criterios.porCancion());
                startActivity(intent1);
                break;
            case "artista":
                Intent intent2=new Intent(this.getActivity(), Fragment_main.class);
                intent2.putExtra("URL", criterios.porArtista());
                startActivity(intent2);
                break;
            case "album":
                Intent intent3=new Intent(this.getActivity(), Fragment_main.class);
                intent3.putExtra("URL", criterios.porAlbum());
                startActivity(intent3);
                break;
        }
    }

}