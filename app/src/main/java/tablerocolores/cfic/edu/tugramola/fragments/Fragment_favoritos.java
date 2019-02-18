package tablerocolores.cfic.edu.tugramola.fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import tablerocolores.cfic.edu.tugramola.R;
import tablerocolores.cfic.edu.tugramola.adapter.FavoritosAdapter;
import tablerocolores.cfic.edu.tugramola.dao.BaseDatosCanciones;
import tablerocolores.cfic.edu.tugramola.dto.Cancion;

public class Fragment_favoritos extends Fragment {

    private RecyclerView recView;

    private List<Cancion> datos;

    private FavoritosAdapter adaptador;
    private static ImageView playButton2;
    private static MediaPlayer reproductor2;
    private static boolean play2;

    public static ImageView getPlayButton() {return playButton2;}

    public static void setPlayButton(ImageView playButton2) {
        Fragment_favoritos.playButton2 = playButton2;}

    public static MediaPlayer getReproductor() {return reproductor2;}

    public static void setReproductor(MediaPlayer reproductor2) {
        Fragment_favoritos.reproductor2 = reproductor2;}

    public static boolean isPlay() {return play2; }

    public static void setPlay(boolean play) { Fragment_favoritos.play2 = play2; }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Context context = getContext();
        BaseDatosCanciones baseDatosCanciones = new BaseDatosCanciones(context,"ITUNESBD",null,1);
        datos = baseDatosCanciones.cargarFavoritos();

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        if (datos.size() >0)
        {
            adaptador = new FavoritosAdapter(context,datos);
            recView = (RecyclerView) view.findViewById(R.id.recyclerView);
            recView.setAdapter(adaptador);
            //recView.setHasFixedSize(true);//opcional, si sé que el tamaño no va a cambiar
            recView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            recView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));


        }
        return view;
    }

}
