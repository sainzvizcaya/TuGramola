package tablerocolores.cfic.edu.tugramola.fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import tablerocolores.cfic.edu.tugramola.R;
import tablerocolores.cfic.edu.tugramola.adapter.FavoritosAdapter;
import tablerocolores.cfic.edu.tugramola.dao.BaseDatosCanciones;
import tablerocolores.cfic.edu.tugramola.dto.Cancion;

public class Fragment_favoritos extends Fragment {

    private RecyclerView recView;

    private List<Cancion> datos;

    private FavoritosAdapter adaptador;
    private static ImageView playButton;
    private static MediaPlayer reproductor;
    private static boolean play;
    private static BaseDatosCanciones baseDatosCanciones;

    public static ImageView getPlayButton() {
        return playButton;
    }

    public static void setPlayButton(ImageView playButton2) {
        Fragment_favoritos.playButton = playButton2;
    }

    public static MediaPlayer getReproductor() {
        return reproductor;
    }

    public static void setReproductor(MediaPlayer reproductor2) {
        Fragment_favoritos.reproductor = reproductor2;
    }

    public static boolean isPlay() {
        return play;
    }

    public static void setPlay(boolean play2) {
        Fragment_favoritos.play = play2;
    }

    public static BaseDatosCanciones getBaseDatosCanciones() {return baseDatosCanciones;}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Context context = getContext();
        baseDatosCanciones = new BaseDatosCanciones(context, "ITUNESBD", null, 1);
        datos = baseDatosCanciones.cargarFavoritos();

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        if (datos != null) {
            if (datos.size() > 0) {
                adaptador = new FavoritosAdapter(context, datos);
                recView = (RecyclerView) view.findViewById(R.id.recyclerView);
                recView.setAdapter(adaptador);
                //recView.setHasFixedSize(true);//opcional, si sé que el tamaño no va a cambiar
                recView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                recView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
                Toast.makeText(getContext(),"Pulsación larga para eliminar de favoritos",Toast.LENGTH_SHORT).show();


            }

        }
        setReproductor(new MediaPlayer());
        setPlay(false);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        return view;
    }


    @Override
    public void onResume() {
        //recView.scrollToPosition(20);
        super.onResume();
    }

    @Override
    public void onStop() {
        try {
            reproductor.stop();
            reproductor.release();
        }
        catch (Exception e)
        {
            // no hacemos nada
            e.printStackTrace();
        }

        super.onStop();
    }
}

