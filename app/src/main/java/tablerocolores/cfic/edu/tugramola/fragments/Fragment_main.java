package tablerocolores.cfic.edu.tugramola.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.media.MediaPlayer;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import tablerocolores.cfic.edu.tugramola.ConexionInternet;
import tablerocolores.cfic.edu.tugramola.R;
import tablerocolores.cfic.edu.tugramola.activities.BusquedaSinResultados;
import tablerocolores.cfic.edu.tugramola.activities.MainActivity;
import tablerocolores.cfic.edu.tugramola.adapter.RecyclerAdapter;
import tablerocolores.cfic.edu.tugramola.dao.BaseDatosCanciones;
import tablerocolores.cfic.edu.tugramola.dto.ResultadoCanciones;


public class Fragment_main extends Fragment {
    RecyclerAdapter adapter;
    android.support.v7.widget.RecyclerView recyclerView;
    private static ImageView playButton;
    private static MediaPlayer reproductor;
    private static boolean play;
    private static ProgressBar progressBar;
    private static TextView sinConexion;
    private static BaseDatosCanciones baseDatosCanciones;
    private static View vista;
    private static String URI_ITUNES="https://itunes.apple.com/search/?media=music&limit=20&term=AC/DC";


    public static ImageView getPlayButton() {return playButton;}

    public static void setPlayButton(ImageView playButton2) {playButton = playButton2;}

    public static MediaPlayer getReproductor() {return reproductor;}

    public static void setReproductor(MediaPlayer reproductor2) {reproductor = reproductor2;}

    public static boolean isPlay() {return play; }

    public static void setPlay(boolean play2) { play = play2; }

    public static BaseDatosCanciones getBaseDatosCanciones() {return baseDatosCanciones;}

    public Fragment_main() {


    }
    public String getURL()
    {
        String url;
        url = MainActivity.Url;
        return url;
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_main, container, false);

        progressBar = vista.findViewById(R.id.progressBar);
        sinConexion = vista.findViewById(R.id.sinConexionTxt);
        sinConexion.setVisibility(View.INVISIBLE);

        if (ConexionInternet.hayInternet(vista.getContext())) {
            String urlBusqueda = getURL();
            setReproductor(new MediaPlayer());
            setPlay(false);
            //El execute llama a doInBackground de la clase de Fragment_DescargarCanciones
            if (urlBusqueda != "") {
                new Fragment_DescargarCanciones(this).execute(urlBusqueda);
            }
            else
            {
                progressBar.setVisibility(View.GONE);
            }
        }
        else {//Toast.makeText(getApplicationContext(),"NO HAY CONEXION A INTERNET",Toast.LENGTH_LONG).show();
            sinConexion.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

        }

        return vista;
    }

    public void mostrarResultados(ResultadoCanciones rc) {   //Aqui quitamos el cargador

        progressBar.setVisibility(View.GONE);

        //Toast.makeText(getApplicationContext(), "DESCARGA COMPLETA", Toast.LENGTH_SHORT).show();

        recyclerView = vista.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(vista.getContext(),LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(vista.getContext(), DividerItemDecoration.VERTICAL));
        adapter = new RecyclerAdapter(vista.getContext(), rc.getResults());


        int m=rc.getResults().size();
        if (m==0) {
            //Intent intent = new Intent(vista.getContext(), BusquedaSinResultados.class);
            //startActivity(intent);
        }
        else
        {  baseDatosCanciones = new BaseDatosCanciones(vista.getContext(),"ITUNESBD",null,1);
            recyclerView.setAdapter(adapter);
            Toast.makeText(getContext(),"Pinche en la estrella para añadir a favoritos",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStop() {
        try {
            reproductor.stop();
            reproductor.release();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        super.onStop();
    }
}
