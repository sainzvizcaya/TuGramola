package tablerocolores.cfic.edu.tugramola.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.design.widget.AppBarLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import tablerocolores.cfic.edu.tugramola.R;
import tablerocolores.cfic.edu.tugramola.activities.MainActivity;
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
    //public MainActivity contenedor;
    private ImageView imagen_buscar;
    BuscarFragment context;
    CriteriosBusqueda criterios;
    private String dato_a_buscar;
    private MainActivity mainActivity;




    public BuscarFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {


        Spinner spinner = view.findViewById(R.id.CmbOpciones);
        ImageView flecha = view.findViewById(R.id.flecha);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.lista_canciones, seleccion);
        spinner.setAdapter(adapter);
        // ocultamos el spinner
        spinner.setVisibility(View.GONE);
        flecha.setVisibility(View.GONE);

        imagen_buscar = view.findViewById(R.id.imagen_buscar);
        editText = (EditText)view.findViewById(R.id.dato);

        imagen_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criterios=new CriteriosBusqueda();
                dato_a_buscar = editText.getText().toString();
                // ocultamos esto ya que solo llamamos a la busqueda total
                /*
                Log.d("LLLL","Seleccionado: "+ seleccion[position]);
                switch (seleccion[position]){
                    case "Todas":
                        MainActivity.Url = criterios.todas(dato_a_buscar);
                        break;
                    case "Cancion":
                        MainActivity.Url = criterios.porCancion(dato_a_buscar);
                        break;
                    case "Artista":
                        MainActivity.Url = criterios.porArtista(dato_a_buscar);
                        break;
                    case "Album":
                        MainActivity.Url = criterios.porAlbum(dato_a_buscar);
                        break;
                }
                */
                // llamamos a la busqueda total ya que las otras no nos devuelven lo que queremos
                MainActivity.Url = criterios.todas(dato_a_buscar);
                // llamamos al metodo para crear el fragment de resultados y lo mostramos en la ventana
                mainActivity = (MainActivity)getActivity();
                TextView t =mainActivity.findViewById(R.id.t_resultados);
                // ocultamos el teclado
                InputMethodManager imm = (InputMethodManager) mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(t.getWindowToken(), 0);
                t.performClick();
            }
        });



        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        vista = inflater.inflate(R.layout.fragment_buscar, container, false);
       // contenedor =  (MainActivity)container.getContext();

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

    /*
    public void buscar_otro(View v){
        Log.d("LLLL","Seleccionado: "+ seleccion[position]);
        CriteriosBusqueda criterios=new CriteriosBusqueda();
        switch (seleccion[position]){
            case "todas":
                MainActivity.Url = criterios.todas();
                break;
            case "cancion":
                MainActivity.Url = criterios.porCancion();
                break;
            case "artista":
                MainActivity.Url = criterios.porArtista();
                break;
            case "album":
                MainActivity.Url = criterios.porAlbum();
                break;
        }
        // llamamos al metodo para crear el fragment de resultados y lo mostramos en la ventana
        //v.findViewById(R.id.t_resultados).performClick();
    }
*/
}